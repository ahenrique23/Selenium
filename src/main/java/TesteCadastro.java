import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;

public class TesteCadastro {
	
	private WebDriver driver;
	private DSL dsl;
	private CadastroPage page;
	
	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
		page = new CadastroPage(driver); 
	}
	
	@After
	public void finaliza(){
		driver.quit();
	}

	@Test
	public void deveRealizarCadastroComSucesso(){
		page.setNome("Ailton");
		page.setSobrenome("Gomes");
		page.setSexoMasculino();
		page.setComidaFavoritaPizza();
		page.setEscolaridade("Mestrado");
		page.setEsporte("Natacao");
		page.cadastrar();
		
		Assert.assertTrue(page.obterResultadoCadastro().startsWith("Cadastrado!"));
		Assert.assertTrue(page.obterNomeCadastro().endsWith("Ailton"));
		Assert.assertEquals("Sobrenome: Gomes", page.obterSobrenomeCadastro());
		Assert.assertEquals("Sexo: Masculino", page.obterDescSexoCadastro());
		Assert.assertEquals("Comida: Pizza", page.obterDescComida());
		Assert.assertEquals("Escolaridade: mestrado", page.obterDescEscolardade());
		Assert.assertEquals("Esportes: Natacao", page.obterDescEsporte());
	}
	
	@Test
	public void deveValidarNomeObrigatorio(){
		page.cadastrar();
		Assert.assertEquals("Nome eh obrigatorio", page.alertObterTextoAceita());
	}
	
	@Test
	public void deveValidarSobrenomeObrigatorio(){
		page.setNome("Nome qualquer");
		page.cadastrar();		
		Assert.assertEquals("Sobrenome eh obrigatorio", page.alertObterTextoAceita());
	}
	
	@Test
	public void deveValidarSexoObrigatorio(){
		page.setNome("Nome qualquer");
		page.setSobrenome("Sobrenome qualquer");
		page.cadastrar();
		Assert.assertEquals("Sexo eh obrigatorio", page.alertObterTextoAceita());
	}
	
	@Test
	public void deveValidarComidaVegetariana(){
		page.setNome("Nome qualquer");
		page.setSobrenome("Sobrenome qualquer");
		page.setSexoFeminino();
		page.setComidaFavoritaCarne();
		page.setComidaFavoritaVegetariano();
		page.cadastrar();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", page.alertObterTextoAceita());
	}
	
	@Test
	public void deveValidarEsportistaIndeciso(){
		page.setNome("Nome qualquer");
		page.setSobrenome("Sobrenome qualquer");
		page.setSexoFeminino();
		page.setComidaFavoritaCarne();
		page.setEsporte("Karate", "O que eh esporte?");
		
		page.cadastrar();
		Assert.assertEquals("Voce faz esporte ou nao?", page.alertObterTextoAceita());
	}
}
