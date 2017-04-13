package translater.dialog;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import translater.config.Config;
import translater.delegate.TranslateDelegate;
import translater.entity.GoogleTRequest;
import translater.entity.TRequest;
import translater.resolver.GoogleResultResolver;

public class TranslationDialog extends Dialog {

	private TranslateDelegate tDelegate;
	private Text qWordsText;
	private Text tWordsText;
	private String qWords = "";
	private String sl;
	private String tl;
	private Combo slCombo;
	private Combo tlCombo;
	private Text translationText;
	private ProgressBar progressBar;
	
	private GoogleResultResolver resolver;

	public TranslationDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	public void create() {
		super.create();
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		resolver = new GoogleResultResolver();
		tDelegate = new TranslateDelegate();
		Composite container = parent;
//		container.setSize(500, 400);
		GridLayout layout = new GridLayout(3, false);
		layout.marginTop = 5;
		layout.marginBottom = 5;
		layout.marginRight = 5;
		layout.marginLeft = 5;
		container.setLayout(layout);

		slCombo = new Combo(container, SWT.NONE);
		Set<String> keySet = Config.langMapping.keySet();
		int i = 0;
		for (String k : keySet) {
			slCombo.add(k, i++);
		}
		slCombo.select(0);

		tlCombo = new Combo(container, SWT.NONE);
		
		final Locale locale = Locale.getDefault();
		String sysLang = locale.getLanguage() + '-' + locale.getCountry();
		String dispalyLang = locale.getDisplayLanguage(Locale.ENGLISH);
		Iterator<String> iter = keySet.iterator();
		i = 0;
		tlCombo.add(dispalyLang, i++);
		while(iter.hasNext()) {
			String k = iter.next();
			if(k.equalsIgnoreCase(dispalyLang))
				continue;
			tlCombo.add(k, i++);
		}
		Config.langMapping.put(dispalyLang, sysLang);
		tlCombo.select(0);

		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
		qWordsText = new Text(container, SWT.BORDER | SWT.SINGLE);
		qWordsText.setLayoutData(gridData);
		qWordsText.setTextLimit(Text.LIMIT);
		qWordsText.setText(qWords);
		
		Label lblTranslate = new Label(container, SWT.NONE);
		GridData gridData2 = new GridData();
		gridData2.horizontalAlignment = SWT.FILL;
		gridData2.grabExcessVerticalSpace = true;
		gridData2.horizontalSpan = 1;
		lblTranslate.setLayoutData(gridData2);
		lblTranslate.setText("Translation:");
		
		progressBar = new ProgressBar(container, SWT.FILL);
		GridData gridData3 = new GridData();
		gridData3.horizontalAlignment = SWT.FILL;
		gridData3.grabExcessVerticalSpace = true;
		gridData3.horizontalSpan = 2;
		
		progressBar.setLayoutData(gridData3);
		progressBar.setMaximum(100);
		progressBar.setSelection(0);
	
		translationText = new Text(container, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
		GridData gridData4 = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData4.horizontalSpan = 3;
		translationText.setLayoutData(gridData4);
		translationText.setEditable(false);
		
		translate();
		return container;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button button = createButton(parent, IDialogConstants.YES_ID, "Translate", true);
		button.addListener(SWT.MouseDown, new Listener() {
			@Override
			public void handleEvent(Event paramEvent) {
				translate();
			}
		});
	}
	
	@Override
	protected Point getInitialSize() {
		return new Point(500, 400);
	}
	
	private void translate() {
		sl = Config.langMapping.get(slCombo.getItem(slCombo.getSelectionIndex()));
		tl = Config.langMapping.get(tlCombo.getItem(tlCombo.getSelectionIndex()));
		List<String> dts = new ArrayList<String>();
		dts.add("t");
		qWords = qWordsText.getText().trim();
		if(qWords == "")
			return;
		progressBar.setSelection(30);
		TRequest tRequest = new GoogleTRequest(sl, tl, qWords, dts, "gtx");
		String translation = tDelegate.translate(tRequest);
		String result = resolver.resolve(translation).getResult();
		progressBar.setSelection(100);
		translationText.setText(shortenText(result, translationText.getParent()));
	}
	
	

	public String getqWords() {
		return qWords;
	}

	public void setqWords(String qWords) {
		this.qWords = qWords;
	}

	public void setqWordsText(String qWords) {
		this.qWordsText.setText(qWords);
	}

	public Text gettWordsText() {
		return tWordsText;
	}

	public void settWordsText(String tWords) {
		this.tWordsText.setText(tWords);
	}

	public String getSl() {
		return sl;
	}

	public void setSl(String sl) {
		this.sl = sl;
	}

	public String getTl() {
		return tl;
	}

	public void setTl(String tl) {
		this.tl = tl;
	}
}