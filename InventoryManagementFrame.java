import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.border.*;

@SuppressWarnings("serial")
public class InventoryManagementFrame extends JFrame {
	private JLabel idLabel;
	private JTextField idTextField;
	private JLabel idAlertLabel;
	private JLabel webIdLabel;
	private JTextField webIdTextField;
	private JLabel webIdAlertLabel;
	private JLabel priceLabel;
	private JTextField priceTextField;
	private JLabel priceAlertLabel;

	public InventoryManagementFrame() {
		super();
		createCompoments();
		createPanel();
		addListeners();
		makeThisVisible();
	}

	private void addListeners() {
		idTextField.addKeyListener(new VIDListener());
		idTextField.setInputVerifier(new VehicleIDVerifier());
	}

	private void createCompoments() {
		idLabel = new JLabel("ID");
		idTextField = new JTextField(10);
		idAlertLabel = new JLabel("ID's length should be 10, only number.");
		idSetTrue();
		priceLabel = new JLabel("Price");
		priceTextField = new JTextField(10);
		priceAlertLabel = new JLabel("Price should be integer.");
		priceSetTrue();
		webIdLabel = new JLabel("WebID");
		webIdTextField = new JTextField(20);
		webIdAlertLabel = new JLabel("Split by \"-\".");
		webIdSetTrue();
	}

	private void createPanel() {
		JPanel componetsPanel = new JPanel();
		componetsPanel.add(idLabel);
		componetsPanel.add(idTextField);
		componetsPanel.add(idAlertLabel);
		componetsPanel.add(webIdLabel);
		componetsPanel.add(webIdTextField);
		componetsPanel.add(webIdAlertLabel);
		componetsPanel.add(priceLabel);
		componetsPanel.add(priceTextField);
		componetsPanel.add(priceAlertLabel);
		this.add(componetsPanel);
	}

	private void makeThisVisible() {
		this.setSize(500, 500);
		this.setVisible(true);
		this.setResizable(false);
	}

	private void idSetWrong() {
		idTextField.setBorder(new LineBorder(Color.red));
		idAlertLabel.setForeground(Color.red);
	}

	private void idSetTrue() {
		idTextField.setBorder(new LineBorder(Color.black));
		idAlertLabel.setForeground(Color.black);
	}
	private void webidSetWrong() {
		webIdTextField.setBorder(new LineBorder(Color.red));
		webIdAlertLabel.setForeground(Color.red);
	}

	private void webIdSetTrue() {
		webIdTextField.setBorder(new LineBorder(Color.black));
		webIdAlertLabel.setForeground(Color.black);
	}

	private void priceSetTrue() {
		priceTextField.setBorder(new LineBorder(Color.black));
		priceAlertLabel.setForeground(Color.black);
	}

	private void priceSetFalse() {
		priceTextField.setBorder(new LineBorder(Color.red));
		priceAlertLabel.setForeground(Color.red);
	}

	private class VIDListener implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

		@Override
		public void keyTyped(KeyEvent e) {
			int keyInput = e.getKeyChar();
			if (keyInput != KeyEvent.VK_ENTER && keyInput != KeyEvent.VK_BACK_SPACE
					&& (keyInput < KeyEvent.VK_0 || keyInput > KeyEvent.VK_9)) {
				idSetWrong();
				e.consume();// invalid numeric input will be eliminated
			}
			String str = idTextField.getText();
			if (keyInput == KeyEvent.VK_ENTER) {
				if (str.length() != 10)
					idSetWrong();
				else
					idSetTrue();
			}
			if (keyInput == KeyEvent.VK_BACK_SPACE) {
				if (str.length() < 10) {
					idSetTrue();
				}
			}
			if (keyInput != KeyEvent.VK_ENTER && keyInput != KeyEvent.VK_BACK_SPACE) {
				if (str.length() == 9)
					idSetTrue();
				if (str.length() > 9) {
					idSetWrong();
					e.consume();
				}
			}
		}
	}

	public class VehicleIDVerifier extends InputVerifier {

		public boolean verify(JComponent input) {
			String vid = ((JTextField) input).getText();
			if (vid.length() == 10) {
				return true;
			} else {
				return false;
			}
		}

		public boolean shouldYieldFocus(JComponent input) {
			boolean valid = verify(input);
			if (!valid) {
				idSetWrong();
			} else {
				idSetTrue();
			}
			return valid;
		}
	}
}
