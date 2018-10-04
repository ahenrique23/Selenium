import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;


public class TesteCampoTreinamento {
	
	private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;
	
	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
		page = new CampoTreinamentoPage(driver);
	}
	
	@After
	public void finaliza(){
		driver.quit();
	}
	
	@Test
	public void testeTextField(){
		page.setNome("Teste de escrita");
		Assert.assertEquals("Teste de escrita", page.obterNomeCadastro());
	}
	
	@Test
	public void testTextFieldDuplo(){
		page.setNome("Ailton");
		Assert.assertEquals("Ailton", page.obterNomeCadastro());
		page.setNome("Gomes");
		Assert.assertEquals("Gomes", page.obterNomeCadastro());
	}
	
	@Test
	public void deveIntegarirComTextArea(){
		page.setSugestoes("testando");
		Assert.assertEquals("testando", page.obterTextoSugestoes());
	}
	
	@Test
	public void deveIntegarirComRadioButton(){
		page.setSexoMasculino();
		Assert.assertTrue(page.obterRadioMarcadoMasculino());
	}
	
	@Test
	public void deveIntegarirComCheckbox(){
		page.setComidaFavoritaPizza();
		Assert.assertTrue(page.obterRadioMarcadoComidaPizza());
	}
	
	@Test
	public void deveIntegarirComCombo(){
		page.setEscolaridade("2o grau completo");
		Assert.assertEquals("Escolaridade: 2o grau completo", page.obterDescEscolardade());
	}
	
	@Test
	public void deveVerificarValoresCombo(){
		Assert.assertEquals(8, dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
		Assert.assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade", "Mestrado"));
	}
	
	@Test
	public void deveVerificarValoresComboMultiplo(){
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");

		List<String> opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(3, opcoesMarcadas.size());
		
		dsl.deselecionarCombo("elementosForm:esportes", "Corrida");
		opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(2, opcoesMarcadas.size());
		Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "O que eh esporte?")));
	}
	
	@Test
	public void deveinteragirComBotoes(){
		dsl.clicarBotao("buttonSimple");
		Assert.assertEquals("Obrigado!", dsl.obterValueElemento("buttonSimple"));
	}
	
	@Test
	public void deveinteragirComLinks(){
		dsl.clicarLink("Voltar");
		
		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
	}
	
	@Test
	public void deveBuscarTextosNaPagina(){
//		Assert.assertTrue(driver.findElement(By.tagName("body"))
//				.getText().contains("Campo de Treinamento"));
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
		
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", 
				dsl.obterTexto(By.className("facilAchar")));
	}
	
}


