package UserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.xml.crypto.dsig.keyinfo.KeyValue;

import Figuras.Figura;
import Figuras.Fim;
import Figuras.Inicio;
import Figuras.Linha;
import Figuras.Oval;
import Figuras.Poligono;
import Figuras.Retangulo;
import Figuras.SubRotina;
import Helpers.ControladorArquivo;
import Helpers.HelperFluxograma;
import Projeto.Fluxograma;
import Projeto.Projeto;

public class Janela extends JFrame {

	// Menus
	private JMenuBar menuBar;
	private JMenu menuArquivo, subMenuArquivo, menuFerramentas;
	private JMenuItem itemSalvar, itemSalvarComo, itemAbrir, itemNovoProjeto,
			itemNovoFluxograma, itemExportarImagem, itemExcluir,
			itemFechar;
	private JPopupMenu menuPopup;

	// Toolbar e botoes
	private JToolBar toolBar;
	private JToggleButton btnInicio, btnFim, btnProcessamento, btnDecisao,
			btnSubrotina, btnLinhaDeFluxo, btnSelecionar;
	private ButtonGroup btnGroup;

	// Paineis
	private JPanel painelLateral, painelCentral;
	private PainelFluxograma painelFluxograma;

	// Lista
	public JList<String> listaFluxogramas;
	private JScrollPane scrollPane;
	public DefaultListModel<String> lP = new DefaultListModel<String>();
	
	// Projeto
	public Projeto projetoAtual;

	private final int SALVAR = 0;
	private final int SALVARCOMO = 1;
	private String nomeProcesso = "";

	// Construtor
	public Janela() {
		super("MeuFluxograma");
		iniciaComponentes();

		this.setSize(900, 700);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
	}

	private void iniciaComponentes() {
		// Barra de ferramentas e botoes
		toolBar = new JToolBar();

		btnGroup = new ButtonGroup();
		btnInicio = new JToggleButton("Inicio");
		btnFim = new JToggleButton("Fim");
		btnProcessamento = new JToggleButton("Processamento");
		btnProcessamento.addActionListener(new tratadorToolbar());
		btnDecisao = new JToggleButton("Decisao");
		btnDecisao.addActionListener(new tratadorToolbar());
		btnSubrotina = new JToggleButton("Subrotina");
		btnSubrotina.addActionListener(new tratadorToolbar());
		btnLinhaDeFluxo = new JToggleButton("Linha de Fluxo");
		btnSelecionar = new JToggleButton("Selecionar");

		toolBar.add(btnInicio);
		toolBar.add(btnFim);
		toolBar.add(btnProcessamento);
		toolBar.add(btnDecisao);
		toolBar.add(btnSubrotina);
		toolBar.add(btnLinhaDeFluxo);
		toolBar.add(btnSelecionar);

		btnGroup.add(btnInicio);
		btnGroup.add(btnFim);
		btnGroup.add(btnProcessamento);
		btnGroup.add(btnDecisao);
		btnGroup.add(btnSubrotina);
		btnGroup.add(btnLinhaDeFluxo);
		btnGroup.add(btnSelecionar);

		// Menus
		menuBar = new JMenuBar();

		menuArquivo = new JMenu("Arquivo");
		menuArquivo.setMnemonic(KeyEvent.VK_A);
		menuFerramentas = new JMenu("Ferramentas");
		menuFerramentas.setMnemonic(KeyEvent.VK_F);
		subMenuArquivo = new JMenu("Novo");
		subMenuArquivo.setMnemonic(KeyEvent.VK_N);

		itemNovoProjeto = new JMenuItem("Projeto");
		itemNovoProjeto.setMnemonic(KeyEvent.VK_P);
		itemNovoProjeto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				KeyEvent.CTRL_MASK));
		itemNovoProjeto.addActionListener(new tratadorMenu());

		itemNovoFluxograma = new JMenuItem("Fluxograma");
		itemNovoFluxograma.setMnemonic(KeyEvent.VK_F);
		itemNovoFluxograma.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
				KeyEvent.CTRL_MASK));
		itemNovoFluxograma.addActionListener(new tratadorMenu());

		itemSalvar = new JMenuItem("Salvar");
		itemSalvar.addActionListener(new tratadorMenu());

		itemSalvarComo = new JMenuItem("Salvar como");
		itemSalvarComo.addActionListener(new tratadorMenu());

		itemAbrir = new JMenuItem("Abrir Projeto");
		itemAbrir.addActionListener(new tratadorMenu());
		itemAbrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				KeyEvent.CTRL_MASK));
		
		itemFechar = new JMenuItem("Fechar");
		itemFechar.addActionListener(new tratadorMenu());

		itemExportarImagem = new JMenuItem("Exportar Imagem");
		itemExportarImagem.addActionListener(new tratadorMenu());
		itemExportarImagem.setMnemonic(KeyEvent.VK_E);
		itemExportarImagem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
				KeyEvent.CTRL_MASK));
		itemExportarImagem.setEnabled(false);

		/*itemVerificaConsistencia = new JMenuItem(
				"Verifica Consistência do Fluxograma");
		itemVerificaConsistencia.addActionListener(new tratadorMenu());
		itemVerificaConsistencia.setMnemonic(KeyEvent.VK_V);
		itemVerificaConsistencia.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_V, KeyEvent.CTRL_MASK));
		itemVerificaConsistencia.setEnabled(false);*/

		menuBar.add(menuArquivo);
		menuBar.add(menuFerramentas);
		menuArquivo.add(subMenuArquivo);
		menuFerramentas.add(itemExportarImagem);
		menuFerramentas.addSeparator();
		//menuFerramentas.add(itemVerificaConsistencia);
		subMenuArquivo.add(itemNovoProjeto);
		subMenuArquivo.add(itemNovoFluxograma);

		menuArquivo.add(itemAbrir);
		menuArquivo.add(itemSalvar);
		menuArquivo.add(itemSalvarComo);
		menuArquivo.addSeparator();
		menuArquivo.add(itemFechar);
		this.setJMenuBar(menuBar);

		menuPopup = new JPopupMenu();
		itemExcluir = new JMenuItem("Excluir");
		itemExcluir.addActionListener(new tratadorPopUp());
		menuPopup.add(itemExcluir);

		// Lista
		listaFluxogramas = new JList<String>();
		listaFluxogramas.addMouseListener(new tratadorMouse());
		scrollPane = new JScrollPane(listaFluxogramas);

		// Paineis
		painelFluxograma = new PainelFluxograma();
		painelFluxograma.setBackground(Color.WHITE);
		painelFluxograma.addMouseListener(new tratadorMouse());
		painelFluxograma.addMouseMotionListener(new trataMouseDragged());
		painelFluxograma.addMouseListener(new MenuPopUp());

		painelLateral = new JPanel(new BorderLayout());
		painelLateral.setBackground(Color.GRAY);
		painelLateral.add(scrollPane);

		painelCentral = new JPanel();
		painelCentral.setLayout(new BorderLayout());
		painelCentral.add(toolBar, BorderLayout.NORTH);
		painelCentral.add(painelFluxograma, BorderLayout.CENTER);

		this.add(painelLateral, BorderLayout.WEST);
		this.add(painelCentral, BorderLayout.CENTER);

		// Separa os paineis lateral e central
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				painelLateral, painelCentral);
		splitPane.setDividerLocation(200);
		getContentPane().add(splitPane);
	}

	class tratadorToolbar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			nomeProcesso = HelperFluxograma.getNomeProcesso(Janela.this);
		}

	}

	class tratadorPopUp implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			painelFluxograma.removeFigura();
		}
	}

	class trataMouseDragged implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {
			// Desenha as figuras selecionas com drag and draw
			if (btnInicio.isSelected() || btnFim.isSelected()
					|| btnProcessamento.isSelected() || btnDecisao.isSelected()
					|| btnSubrotina.isSelected()
					|| btnLinhaDeFluxo.isSelected()) {
				Figura figura = painelFluxograma.getUltimaFigura();
				figura.setPontoFinal(e.getX(), e.getY());
			}
			// Movimenta a figura selecionada
			else if (btnSelecionar.isSelected()) {
				Figura figura = painelFluxograma.getSelecionado();
				if (figura != null && figura.intersecta(e.getX(), e.getY())) {
					figura.moveTo(e.getX(), e.getY());
				}
			}
			painelFluxograma.repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {

		}

	}

	class tratadorMenu implements ActionListener {
		JFileChooser fileChooser = new JFileChooser();

		@Override
		public void actionPerformed(ActionEvent e) {
			// Cria novo projeto
			if (e.getSource().equals(itemNovoProjeto)) {
				projetoAtual = new Projeto(JOptionPane.showInputDialog(
						Janela.this, "Digite o nome do projeto:"));
				HelperFluxograma.defineTitulo(Janela.this, projetoAtual.getNomeProjeto());
				painelFluxograma.limpaFiguras();
				lP.clear();
				listaFluxogramas.setModel(lP);
			}
			// Cria novo fluxograma
			else if (e.getSource().equals(itemNovoFluxograma)) {
				if (projetoAtual != null) {
					HelperFluxograma.adicionaFluxograma(Janela.this, painelFluxograma);
					HelperFluxograma.habilitaFuncoesFluxograma(itemExportarImagem);
				} else {
					JOptionPane.showMessageDialog(Janela.this,
							"Crie um novo projeto!");
				}
			}
			// Opcao Salvar Como
			else if (e.getSource().equals(itemSalvarComo)) {
				fileChooser
						.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int returnVal = fileChooser.showSaveDialog(Janela.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					try {
						ControladorArquivo.salvarDados(file, SALVARCOMO, projetoAtual);
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(Janela.this,
								"Erro ao salvar no arquivo");
						ex.printStackTrace();
					}
				}
			}
			// Opcao Salvar
			else if (e.getSource().equals(itemSalvar)) {
				File file = projetoAtual.getArquivoProjeto();
				try {
					ControladorArquivo.salvarDados(file, SALVAR, projetoAtual);
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(Janela.this,
							"Erro ao salvar no arquivo");
					ex.printStackTrace();
				}
			}
			// Opcao Abrir Projeto
			else if (e.getSource().equals(itemAbrir)) {
				int returnVa = fileChooser.showOpenDialog(Janela.this);
				if (returnVa == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					try {
						ControladorArquivo.carregaDados(file, Janela.this);
						JOptionPane.showMessageDialog(Janela.this,
								"Arquivo carregado com sucesso!");
					} catch (Exception exc) {
						JOptionPane.showMessageDialog(Janela.this,
								"Erro ao ler do arquivo");
					}
				}
			}else if(e.getSource().equals(itemFechar)){
				System.exit(0);
			}
			// Opcao exportar imagem
			else if (e.getSource().equals(itemExportarImagem)) {
				if (projetoAtual.getFluxogramas() != null) {
					ControladorArquivo.exportaImagem(painelFluxograma,Janela.this);
				} else {
					JOptionPane.showMessageDialog(Janela.this,
							"Nenhum fluxograma para ser exportado!");
				}
			}
		}
	}// end tratadorMenu

	class tratadorMouse implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			// Seleciona o fluxograma na lista
			if (e.getSource().equals(listaFluxogramas)
					&& e.getClickCount() == 2) {
				painelFluxograma.setFluxogramaAtual(projetoAtual
						.getFluxogramas().get(
								listaFluxogramas.getSelectedIndex()));
			}
			// Abre o fluxograma da subrotina selecionada
			else if (e.getSource().equals(painelFluxograma)
					&& btnSelecionar.isSelected() && e.getClickCount() == 2) {
				// Pega a figura seleciona e verifica se mesma é subrotina
				Figura figura = painelFluxograma.getSelecionado();
				if (figura != null && figura.intersecta(e.getX(), e.getY())
						&& figura instanceof SubRotina) {
					SubRotina subRotina = (SubRotina) figura;
					// Define o fluxograma atual de acordo com a subrotina
					painelFluxograma.setFluxogramaAtual(subRotina
							.getFluxogramaRelacionado());
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (projetoAtual == null) {
				JOptionPane.showMessageDialog(Janela.this,
						"Você deve criar um novo projeto!");
			} else {
				if (projetoAtual.getFluxogramas() == null) {
					JOptionPane.showMessageDialog(Janela.this,
							"Você deve criar um novo fluxograma!");
				} else {
					String retorno = null;
					// Cria as figuras de acordo com o botão selecionado e as
					// adiciona no fluxograma
					if (e.getSource().equals(painelFluxograma)
							&& e.getButton() == MouseEvent.BUTTON1) {
						if (btnInicio.isSelected()) {
							if(!projetoAtual.getFluxogramaAtual(
									listaFluxogramas.getSelectedIndex())
									.adicionaOvalComRegra(
											new Inicio(e.getX(), e.getY(),
													"Inicio"))){
								JOptionPane.showMessageDialog(Janela.this, "Este fluxograma já possui início!","Erro",JOptionPane.ERROR_MESSAGE);
							}
						} else if (btnFim.isSelected()) {
							retorno = projetoAtual
									.getFluxogramaAtual(
											listaFluxogramas.getSelectedIndex())
									.verificaSePodeAdicionar(
											new Fim(e.getX(), e.getY(), "Fim"));
						} else if (btnProcessamento.isSelected()) {
							retorno = projetoAtual.getFluxogramaAtual(
									listaFluxogramas.getSelectedIndex())
									.verificaSePodeAdicionar(
											new Retangulo(e.getX(), e.getY(),
													nomeProcesso));
						} else if (btnDecisao.isSelected()) {
							retorno = projetoAtual.getFluxogramaAtual(
									listaFluxogramas.getSelectedIndex())
									.verificaSePodeAdicionar(
											new Poligono(e.getX(), e.getY(),
													nomeProcesso));
						} else if (btnLinhaDeFluxo.isSelected()) {
							Linha linha = new Linha(e.getX(), e.getY(), "");
							// Define a linha que esta sendo criada como proxima
							// da ultima figura
							projetoAtual
									.getFluxogramaAtual(
											listaFluxogramas.getSelectedIndex())
									.getUltimaNaoLinha().setLinhaProx(linha);
							projetoAtual.getFluxogramaAtual(
									listaFluxogramas.getSelectedIndex())
									.adicionaFigura(linha);
							// Relaciona a linha com a figura anterior
							linha.setFiguraAnt(projetoAtual
									.getUltimoFluxograma()
									.getFiguras()
									.get(projetoAtual
											.getFluxogramaAtual(
													listaFluxogramas
															.getSelectedIndex())
											.getFiguras().size() - 2));
						} else if (btnSubrotina.isSelected()) {
							retorno = projetoAtual.getFluxogramaAtual(
									listaFluxogramas.getSelectedIndex())
									.verificaSePodeAdicionar(
											new SubRotina(e.getX(), e.getY(),
													nomeProcesso));
						} else if (btnSelecionar.isSelected()) {
							painelFluxograma
									.verificaSelecao(e.getX(), e.getY());
						}
						if(retorno != null) JOptionPane.showMessageDialog(Janela.this, retorno, "Erro", JOptionPane.ERROR_MESSAGE);
						painelFluxograma.repaint();
					}
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (e.getSource().equals(painelFluxograma)
					&& e.getButton() == MouseEvent.BUTTON1) {
				// Após criar a figura de subrotina verifica a qual fluxograma a
				// mesma sera vinculada
				if (btnSubrotina.isSelected()) {
					int opt = Integer.parseInt(JOptionPane.showInputDialog(
							Janela.this, "Vincular esta subrotina a:\n"
									+ "1 - Novo fluxograma\n"
									+ "2 - Fluxograma existente"));
					SubRotina subRotina;
					switch (opt) {
					case 1:
						// cria novo fluxograma para a subrotina criada
						subRotina = (SubRotina) projetoAtual
								.getUltimoFluxograma().getUltimaFigura();
						HelperFluxograma.adicionaFluxograma(Janela.this, painelFluxograma);
						subRotina.setFluxogramaRelacionado(projetoAtual
								.getUltimoFluxograma());
						break;
					case 2:
						if (projetoAtual != null) {
							String lista = "";
							// Armazena os fluxogramas existentes em uma String
							for (int i = 0; i < projetoAtual.getFluxogramas()
									.size(); i++) {
								lista += i
										+ " - "
										+ projetoAtual.getFluxogramas().get(i)
												.getNomeFluxograma() + "\n";
							}
							int opcao = Integer.parseInt(JOptionPane
									.showInputDialog(Janela.this,
											"Escolha o fluxograma:\n" + lista));
							subRotina = (SubRotina) projetoAtual
									.getFluxogramaAtual(
											listaFluxogramas.getSelectedIndex())
									.getUltimaFigura();
							subRotina.setFluxogramaRelacionado(projetoAtual
									.getFluxogramas().get(opcao));
							break;
						}
					}
				}
			}
		}
	}// end tratadorMouse

	private class MenuPopUp extends MouseAdapter {
		@Override
		public void mouseReleased(MouseEvent e) {
			if (painelFluxograma.getSelecionado() != null) {
				// previne que o menu popup abra se o usuario clicar fora da
				// area da figura selecionada
				Figura figura = painelFluxograma.getSelecionado();
				if (figura != null && figura.intersecta(e.getX(), e.getY())) {
					if (e.getButton() == MouseEvent.BUTTON3) {
						menuPopup.show(e.getComponent(), e.getX(), e.getY());
					}
				}
			}
		}
	}// end MenuPopUp
}