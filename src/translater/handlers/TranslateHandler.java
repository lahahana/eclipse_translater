package translater.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import translater.dialog.TranslationDialog;

public class TranslateHandler extends AbstractHandler {
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		ISelection iSelection = HandlerUtil.getCurrentSelection(event);
		String qWords = ((TextSelection)iSelection).getText();
		TranslationDialog dialog = new TranslationDialog(window.getShell());
		dialog.setqWords(qWords);
		dialog.open();

		return null;
	}
}
