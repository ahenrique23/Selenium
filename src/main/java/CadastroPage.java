import org.openqa.selenium.WebDriver;

public class CadastroPage {

	
	private DSL dsl;	

	public CadastroPage(WebDriver driver){
		dsl = new DSL(driver);
	}
	
	public void setNome(String nome){
		dsl.escrever("elementosForm:nome", nome);
	}
	
	public void setSobrenome(String sobrenome) {
		dsl.escrever("elementosForm:sobrenome", sobrenome);
	}
	
	public void setSexoMasculino() {
		dsl.clicarRadio("elementosForm:sexo:0");
	}
	public void setSexoFeminino() {
		dsl.clicarRadio("elementosForm:sexo:1");
	}
	
	public void setComidaFavoritaPizza() {
		dsl.clicarRadio("elementosForm:comidaFavorita:2");
	}
	
	public void setComidaFavoritaCarne() {
		dsl.clicarRadio("elementosForm:comidaFavorita:0");
	}
	
	public void setComidaFavoritaVegetariano() {
		dsl.clicarRadio("elementosForm:comidaFavorita:3");
	}
	public void setEscolaridade(String valor) {
		dsl.selecionarCombo("elementosForm:escolaridade", valor);
	}
	
	public void setEsporte(String... valores) {
		for(String valor : valores)
		dsl.selecionarCombo("elementosForm:esportes", valor);
	}
	
	public void cadastrar() {
		dsl.clicarBotao("elementosForm:cadastrar");
	}
	
	public String obterResultadoCadastro() {
		return  dsl.obterTexto("resultado");
	}
	
	public String obterNomeCadastro() {
		return dsl.obterTexto("descNome");
	}
	
	public String obterSobrenomeCadastro() {
		return dsl.obterTexto("descSobrenome");
	}
	
	public String obterDescSexoCadastro() {
		return dsl.obterTexto("descSexo");
	}
	
	public String obterDescComida() {
		return dsl.obterTexto("descComida");
	}
	
	public String obterDescEscolardade() {
		return dsl.obterTexto("descEscolaridade");
	}
	
	public String obterDescEsporte() {
		return dsl.obterTexto("descEsportes");
	}
	
	public String alertObterTextoAceita() {
		return dsl.alertaObterTextoEAceita();
	}
	
	
	
	
}
