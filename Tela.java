
/*
 * 
Projeto - Avaliação Final

Grupo:
Luís Eduardo Dalle Molle Bacelar Carvalho Santana - 22104765
Gabriel Carlete de Amorim - 22100699
Raissa Abate Britto - 22108822

*/

package projeto;

import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Tela extends JFrame{
	int c=0;
	public static void main(String[] args){
		Tela tela1 = new Tela();
		tela1.telaPrincipal();
	}
	
	public void telaPrincipal(){
		Container painel = getContentPane();
		setLayout(null);	
		
		// Imagem
		ImageIcon ppt = new ImageIcon(getClass().getResource("pedra-papel-tesoura.png"));
		JLabel ppt1 = new JLabel(ppt);
		ppt1.setBounds(-10,80,799,255);
		
		// Fontes
		Font titulo = new Font("Arial",Font.PLAIN | Font.BOLD,20);
		Font corpo = new Font("Arial",Font.PLAIN,15);
		Font titulo2 = new Font("Arial",Font.PLAIN | Font.BOLD,30);
		
		// Textos
		JLabel lTituloPrincipal = new JLabel("Bem-Vindo ao Pedra-Papel-Tesoura");
		lTituloPrincipal.setBounds(140,10,550,50);
		lTituloPrincipal.setFont(titulo2);
		
		JLabel lLogin = new JLabel("LOGIN");
		lLogin.setBounds(190,360,50,50);
		lLogin.setFont(corpo);
		
		JLabel lSenha = new JLabel("SENHA");
		lSenha.setBounds(190,460,50,50);
		lSenha.setFont(corpo);
		
		// Estrada de Texto
		JTextField tfLogin = new JTextField();
		tfLogin.setBounds(250,360,300,50);
		
		JTextField tfSenha = new JPasswordField();
		tfSenha.setBounds(250,460,300,50);

		// Botões
		JButton bLogin = new JButton("Logar");
		bLogin.setBounds(200,690,400,100);
		bLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

;				final UsuarioDAO dao = new UsuarioDAO();
		        try {
					if (dao.checkLogin(tfLogin.getText(), tfSenha.getText())) {
						dispose();
						Tela tl = new Tela();
						tl.telaConta();
					}
					else {
					    JOptionPane.showMessageDialog(null, "Senha ou Login incorretos!");
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		bLogin.setFont(titulo);
		
		
		JButton bCadastro = new JButton("Fazer Cadastro");
		bCadastro.setBounds(200,840,400,100);
		bCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tela tl = new Tela();
				try {
					dispose();
					tl.telaCadastro();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		bCadastro.setFont(titulo);
		
		
		// Adicionando Elementos ao Painel
		painel.add(lTituloPrincipal);
		painel.add(lLogin);
		painel.add(tfLogin);
		painel.add(lSenha);
		painel.add(tfSenha);
		painel.add(ppt1);
		painel.add(bLogin);
		painel.add(bCadastro);
		
		
		setResizable(false);
		setSize(800,1040);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	
	//////////////////////////////////////////////////////////////////
	
	
	public void telaCadastro() throws SQLException{
		Container painel = getContentPane();
		setLayout(null);
	
				
				// Fontes
				Font titulo = new Font("Arial",Font.PLAIN | Font.BOLD,20);
				Font corpo = new Font("Arial",Font.PLAIN,15);
				Font titulo2 = new Font("Arial",Font.PLAIN | Font.BOLD,30);
				
				// Textos
				JLabel lTituloPrincipal = new JLabel("Tela de Cadastro");
				lTituloPrincipal.setBounds(280,10,300,50);
				lTituloPrincipal.setFont(titulo2);
				
				JLabel lLogin = new JLabel("LOGIN");
				lLogin.setBounds(250,110,50,50);
				lLogin.setFont(corpo);
				
				JLabel lSenha = new JLabel("SENHA");
				lSenha.setBounds(250,210,50,50);
				lSenha.setFont(corpo);
				
				JLabel lConfirmarSenha = new JLabel("CONFIRMAR SENHA");
				lConfirmarSenha.setBounds(250,310,200,50);
				lConfirmarSenha.setFont(corpo);
				
				// Estrada de Texto
				JTextField tfLogin = new JTextField();
				tfLogin.setBounds(250,160,300,50);
				
				
				JTextField tfSenha = new JPasswordField();
				tfSenha.setBounds(250,260,300,50);

				// Botões
				
				JButton bVoltar = new JButton("Voltar");
				bVoltar.setBounds(200,840,400,100);
				bVoltar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Tela tl = new Tela();
						dispose();
						tl.telaPrincipal();
					}
				});
				bVoltar.setFont(titulo);
				
				JButton bCadastrar = new JButton("Cadastrar");
				bCadastrar.setBounds(250,360,300,50);
				bCadastrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Connection conn;
						try {
							conn = GerarConexao.criaConexao();
							PreparedStatement ps;
							String sql = "INSERT INTO tb_infos (login,senha) VALUES (?,?);";
							ps = conn.prepareStatement(sql);
							ps.setString(1, tfLogin.getText());
							ps.setString(2, tfSenha.getText());
							int rs = ps.executeUpdate();
						
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	
						
						Tela tl = new Tela();
						dispose();
						tl.telaPrincipal();
					}
				});
				bCadastrar.setFont(titulo);
				
				
				// Adicionando Elementos ao Painel
				painel.add(lTituloPrincipal);
				painel.add(lLogin);
				painel.add(tfLogin);
				painel.add(lSenha);
				painel.add(tfSenha);
				//painel.add(lConfirmarSenha);
				//painel.add(tfConfirmarSenha);
				
				
				painel.add(bVoltar);
				painel.add(bCadastrar);
		
		setResizable(false);
		setSize(800,1040);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public void telaConta() {
		Container painel = getContentPane();
		setLayout(null);
		
		// Fontes
		Font titulo = new Font("Arial",Font.PLAIN | Font.BOLD,20);	
		Font titulo2 = new Font("Arial",Font.PLAIN | Font.BOLD,30);
		Font corpoB = new Font("Arial",Font.PLAIN | Font.BOLD,15);
		Font corpo2 = new Font("Arial",Font.PLAIN,12);
		
		// Textos
		JLabel lTituloPrincipal = new JLabel("Sua Conta");
		lTituloPrincipal.setBounds(330,10,200,50);
		lTituloPrincipal.setFont(titulo2);
		
		JLabel lLogin = new JLabel("     LOGIN");
		lLogin.setBounds(20,110,150,50);
		lLogin.setFont(corpoB);
		
		JLabel lSenha = new JLabel("     SENHA");
		lSenha.setBounds(20,190,250,50);
		lSenha.setFont(corpoB);
		
		JLabel lApagar = new JLabel("APAGAR CONTA");
		lApagar.setBounds(20,270,250,50);
		lApagar.setFont(corpoB);
		
		JLabel lApagarSub = new JLabel("APAGAR CONTA E SUAS INFORMAÇÔES"); 
		lApagarSub.setBounds(20,300,400,50);
		lApagarSub.setFont(corpo2);
		
		// Botões
		
		JButton bAlteraLogin = new JButton("Alterar Login");
		bAlteraLogin.setBounds(120,120,200,30);
		bAlteraLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String velhoLogin = JOptionPane.showInputDialog("Insira o ANTIGO Login: "); 
				String novoLogin = JOptionPane.showInputDialog("Insira o NOVO Login: ");
				Connection conn;
				try {
					conn = GerarConexao.criaConexao();
					PreparedStatement ps;
					String sql = "UPDATE tb_infos SET login=? WHERE login=?;";
					ps = conn.prepareStatement(sql);
					ps.setString(1, novoLogin);
					ps.setString(2, velhoLogin);
					int rs = ps.executeUpdate();
				
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Tela tl = new Tela();
				dispose();
				tl.telaConta();
			}
		});
		bAlteraLogin.setFont(titulo);
		
		JButton bAlteraSenha = new JButton("Alterar Senha");
		bAlteraSenha.setBounds(120,200,200,30);
		bAlteraSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String velhoSenha = JOptionPane.showInputDialog("Insira a ANTIGA Senha: "); 
				String novoSenha = JOptionPane.showInputDialog("Insira a NOVA Senha: ");
				Connection conn;
				try {
					conn = GerarConexao.criaConexao();
					PreparedStatement ps;
					String sql = "UPDATE tb_infos SET senha=? WHERE senha=?;";
					ps = conn.prepareStatement(sql);
					ps.setString(1, novoSenha);
					ps.setString(2, velhoSenha);
					int rs = ps.executeUpdate();
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Tela tl = new Tela();
				dispose();
				tl.telaConta();
			}
		});
		bAlteraSenha.setFont(titulo);
		

		
		JButton bApagarConta = new JButton("Apagar Conta");
		bApagarConta.setBounds(150,280,200,30);
		bApagarConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String loginApagar = JOptionPane.showInputDialog("Insira o login a apagar: ");
				Connection conn;
				try {
					conn = GerarConexao.criaConexao();
					PreparedStatement ps;
					String sql = "DELETE FROM tb_infos WHERE login=?;";
					ps = conn.prepareStatement(sql);
					ps.setString(1, loginApagar);
					int rs = ps.executeUpdate();
				
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				Tela tl = new Tela();
				dispose();
				tl.telaPrincipal();
			}
		});
		bApagarConta.setFont(titulo);
		
		JButton bVoltar = new JButton("Voltar");
		bVoltar.setBounds(200,840,400,100);
		bVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tela tl = new Tela();
				dispose();
				tl.telaPrincipal();
			}
		});
		bVoltar.setFont(titulo);
		
		JButton bJogar = new JButton("Jogar");
		bJogar.setBounds(200,690,400,100);
		bJogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tela tl = new Tela();
				dispose();
				tl.telaJogo();
			}
		});
		bJogar.setFont(titulo);
		
		
		
		// Adicionando Elementos ao Painel
		painel.add(lTituloPrincipal);
		painel.add(lLogin);
		painel.add(lSenha);
		painel.add(lApagar);
		painel.add(lApagarSub);
		
		
		painel.add(bApagarConta);
		painel.add(bAlteraSenha);
		painel.add(bAlteraLogin);
		painel.add(bVoltar);
		painel.add(bJogar);
		
		setResizable(false);
		setSize(800,1040);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	public void telaJogo()  {
		
		Container painel = getContentPane();
		setLayout(null);

				// Imagens
				ImageIcon pedra = new ImageIcon(getClass().getResource("Pedra.png"));
				JLabel pedra1 = new JLabel(pedra);
				pedra1.setBounds(20,120,230,223);
				
				ImageIcon papel = new ImageIcon(getClass().getResource("Papel.png"));
				JLabel papel1 = new JLabel(papel);
				papel1.setBounds(20,350,230,223);
				
				ImageIcon tesoura = new ImageIcon(getClass().getResource("Tesoura.png"));
				JLabel tesoura1 = new JLabel(tesoura);
				tesoura1.setBounds(20,580,230,223);
				
				// Fontes
				Font titulo = new Font("Arial",Font.PLAIN | Font.BOLD,20);
				Font titulo2 = new Font("Arial",Font.PLAIN | Font.BOLD,30);
		
				// Textos
				JLabel lTituloPrincipal = new JLabel("Pedra-Papel-Tesoura");
				lTituloPrincipal.setBounds(250,10,400,50);
				lTituloPrincipal.setFont(titulo2);
				
				JLabel lEntrada = new JLabel("Escolha sua jogada");
				lEntrada.setBounds(40,60,200,50);
				lEntrada.setFont(titulo);
				
				
				// Estrada de Texto
				
				
				// Botões
				
				JButton bVoltar = new JButton("Voltar");
				bVoltar.setBounds(200,840,400,100);
				bVoltar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Tela tl = new Tela();
						dispose();
						tl.telaConta();
					}
				});
				bVoltar.setFont(titulo);
				
				JButton bPedra = new JButton("PEDRA");
				bPedra.setBounds(300,200,200,50);
				bPedra.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int numeroComputador=-1;
						String escolhaComputador="";
						String situacao=""; 
						
						// 2 = Pedra, 1=Papel e 0=Tesoura
						numeroComputador=(int) (Math.random()*3);
						if (numeroComputador==2) {
							escolhaComputador="PEDRA";
							situacao="s empataram!!";
						} else if (numeroComputador==1) {
							escolhaComputador="Papel";
							situacao=" perdeu!!";
						} else if (numeroComputador==0) {
							escolhaComputador="Tesoura";
							situacao=" ganhou!!";
						}
							
						JOptionPane.showMessageDialog(null, "Seu oponente escolheu: "+escolhaComputador+"\n"+"Você"+situacao);

					}
				});
				bPedra.setFont(titulo);
				
				JButton bPapel = new JButton("PAPEL");
				bPapel.setBounds(300,420,200,50);
				bPapel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int numeroComputador=-1;
						String escolhaComputador="";
						String situacao=""; 
						// 2 = Pedra, 1=Papel e 0=Tesoura
						numeroComputador=(int) (Math.random()*3);
						if (numeroComputador==2) {
							escolhaComputador="PEDRA";
							situacao=" ganhou!!";
						} else if (numeroComputador==1) {
							escolhaComputador="Papel";
							situacao="s empataram!!";
						} else if (numeroComputador==0) {
							escolhaComputador="Tesoura";
							situacao=" perdeu!!";
						}
							
						JOptionPane.showMessageDialog(null, "Seu oponente escolheu: "+escolhaComputador+"\n"+"Você"+situacao);

					}
				});
				bPapel.setFont(titulo);
				
				JButton bTesoura = new JButton("TESOURA");
				bTesoura.setBounds(300,640,200,50);
				bTesoura.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int numeroComputador=-1;
						String escolhaComputador="";
						String situacao=""; 
						// 2 = Pedra, 1=Papel e 0=Tesoura
						numeroComputador=(int) (Math.random()*3);
						if (numeroComputador==2) {
							escolhaComputador="PEDRA";
							situacao=" perdeu!!";
						} else if (numeroComputador==1) {
							escolhaComputador="Papel";
							situacao=" ganhou!!";
						} else if (numeroComputador==0) {
							escolhaComputador="Tesoura";
							situacao="s empataram!!";
						}
							
						JOptionPane.showMessageDialog(null, "Seu oponente escolheu: "+escolhaComputador+"\n"+"Você"+situacao);

					}
				});
				bTesoura.setFont(titulo);
				
				// Adicionando Elementos ao Painel
				painel.add(lTituloPrincipal);
				painel.add(pedra1);
				painel.add(papel1);
				painel.add(tesoura1);
				painel.add(lEntrada);

				
				painel.add(bVoltar);
				painel.add(bPedra);
				painel.add(bPapel);
				painel.add(bTesoura);

		
		setResizable(false);
		setSize(800,1040);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}