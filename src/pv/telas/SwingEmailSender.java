package pv.telas;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import pv.modelo.ConfigUtility;
import pv.modelo.EmailUtility;
import pv.modelo.Usuario;
import pv.modelo.dao.UsuarioDAO;

public class SwingEmailSender extends JFrame {
	private ConfigUtility configUtil = new ConfigUtility();
	private JLabel labelTo = new JLabel("Email para: ");
        private JLabel labelNome = new JLabel("Nome do usuário: ");
	
	private JTextField fieldTo = new JTextField(30);
        private JTextField fieldNome = new JTextField(30);
	//private JTextField fieldSubject = new JTextField(30);
	
	private JButton buttonSend = new JButton("Enviar");	
	private JTextArea textAreaMessage = new JTextArea(10, 30);
	
	private GridBagConstraints constraints = new GridBagConstraints();
	
	public SwingEmailSender() {
		super("Baú de historinhas - recuperar senha");
		
		// set up layout
		setLayout(new GridBagLayout());
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(5, 5, 5, 5);
	
		//setupMenu();
		setupForm();
		
		pack();
                setResizable(false);
		setLocationRelativeTo(null);	// center on screen
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		
        }
	
	private void setupForm() {
		constraints.gridx = 0;
		constraints.gridy = 0;
		add(labelNome, constraints);
		
		constraints.gridx = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		add(fieldNome, constraints);
		
		/*constraints.gridx = 0;
		constraints.gridy = 1;
		add(labelTo, constraints);
                
                
		constraints.gridx = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		add(fieldTo, constraints);*/
		
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridheight = 2;
		constraints.fill = GridBagConstraints.BOTH;
		buttonSend.setFont(new Font("Arial", Font.BOLD, 16));
		add(buttonSend, constraints);
		
		buttonSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				buttonSendActionPerformed(event);
			}
		});
		
		/*constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridheight = 1;
		constraints.gridwidth = 3;
		
		constraints.gridy = 3;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		
		add(new JScrollPane(textAreaMessage), constraints);	*/
                
	}
	
	private void buttonSendActionPerformed(ActionEvent event) {
		/*if (!validateFields()) {
			return;
		}*/
		
		String toAddress = fieldTo.getText();
		String subject="Recuperação da senha do Baú de historinhas";// = fieldSubject.getText();
		String message;
		
		File[] attachFiles = null;

		try {
                    boolean flag = false;
                    UsuarioDAO dao = new UsuarioDAO();
                    List<Usuario> usuarios = dao.buscarTodos();
                    for (int i = 0; i < usuarios.size(); i++) {

                        if (usuarios.get(i).getNome().equalsIgnoreCase(fieldNome.getText())) { 
                            toAddress = usuarios.get(i).getEmail();
                            message = "Sua senha do baú de historinhas é : " + usuarios.get(i).getSenha();
                            Properties smtpProperties = configUtil.loadProperties();
                            EmailUtility.sendEmail(smtpProperties, toAddress, subject, message, attachFiles);	
                            JOptionPane.showMessageDialog(this,"Email enviado com sucesso!");
                            setVisible(false);
                            flag = true;
                        }

                    }
                    
                    if(!flag){
                        
                        JOptionPane.showMessageDialog(this,"Usuário não existe, desculpe");
                    }
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this,"Erro enquanto enviava o email\natualize seu email e/ou verifique sua conexão com a internet ( não envia email sem internet )" ,"", JOptionPane.ERROR_MESSAGE);
		}       
	}
	
	/*private boolean validateFields() {
		if (fieldTo.getText().equals("")) {
			JOptionPane.showMessageDialog(this, 
					"Insira o email por favor!",
					"Error", JOptionPane.ERROR_MESSAGE);
			fieldTo.requestFocus();
			return false;
		}
		
		return true;
	}*/
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new SwingEmailSender().setVisible(true);
			}
		});
	}
}