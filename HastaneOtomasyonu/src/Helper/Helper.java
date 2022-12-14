package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {
	/**
	 * 
	 * 
	 * @methods
	 * 
	 * 
	 */
	public static void optionPaneChangeButtonText() {
		
		UIManager.put("OptionPane.cancelButtonText", "İptal");
		UIManager.put("OptionPane.noButtonText", "Hayır");
		UIManager.put("OptionPane.okButtonText", "Tamam");
		UIManager.put("OptionPane.yesButtonText", "Evet");
		
	}
	/**
	 * 
	 * @param str
	 * 
	 */
	public static void showMessage(String str) {
		
		Helper.optionPaneChangeButtonText();
		String msg;
		switch(str) {
		 	case "fill":
		 		msg = "Lütfen tüm alanları doldurunuz!";
		 		break;
		 	case "success":
		 		msg = "İşlem başarılı!";
		 		break;	
		 	default:
		 		msg = str;
		 		break;
			 
		}
		
		JOptionPane.showMessageDialog(null, msg,"Mesaj",JOptionPane.INFORMATION_MESSAGE);
	}
	/**
	 * 
	 * @param str
	 * 
	 */
	public static boolean confrim(String str) {
		Helper.optionPaneChangeButtonText();
		String msg;
		switch(str) {
			case "sure":
				msg = "Bu işlemi gerçekleştirmek istiyor musunuz?";
				break;
			default:
				msg = str;
		}
		
		int result = JOptionPane.showConfirmDialog(null, msg, "Dikkat!", JOptionPane.YES_NO_OPTION);
		/**
		 * 0 -> 'Yes'
		 * 1 -> 'No'
		 */
		if(result == 0)
			return true;
		else
			return false;
		
	}
	
}
