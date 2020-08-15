package io.github.leobugoni.pessoaFisica;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.leobugoni.pessoaFisica.model.PessoaFisica;
import io.github.leobugoni.pessoaFisica.repository.RepositoryPessoaFisica;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Random;

import static java.lang.String.valueOf;
import static org.springframework.http.HttpMethod.POST;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private RepositoryPessoaFisica repositoryPessoaFisica;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws JsonProcessingException {
		gerarPessoas();
//		for (int i = 0; i < 10; i++) {
//			final PessoaFisica pessoaFisica = new PessoaFisica(
//					"LEONARDO BUGONI",
//					gerarDataNascimento(),
//					"ANALISTA",
//					gerarNumeroEntre(3, 120),
//					new BigDecimal(valueOf(gerarNumeroEntre(1000, 20000))),
//					690,
//					gerarCondicao(),
//					gerarCondicao(),
//					gerarCEP(),
//					"CHAPECÓ",
//					"SC",
//					gerarCondicao());
//			repositoryPessoaFisica.save(pessoaFisica);
//		}
	}

	private String gerarCEP(){
		final int primeiraParte = gerarNumeroEntre(10000,99999);
		final int segundaParte = gerarNumeroEntre(111,999);
		return primeiraParte + "-" + segundaParte;
	}

	private boolean gerarCondicao(){
		return gerarNumeroEntre(0, 1) == 1;
	}

	private LocalDate gerarDataNascimento(){
		return LocalDate.now().minusDays(gerarNumeroEntre(6570, 21900));
	}

	private int gerarNumeroEntre(final int menor, final int maior){
		return new Random().nextInt(maior-menor) + menor;
	}


//	private void request(){
//		OkHttpClient client = new OkHttpClient().newBuilder()
//				.build();
//		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
//		RequestBody body = RequestBody.create(mediaType, "acao=gerar_pessoa&sexo=I&pontuacao=S&idade=0&cep_estado=&txt_qtde=03&cep_cidade=");
//		Request request = new Request.Builder()
//				.url("https://www.4devs.com.br/ferramentas_online.php")
//				.method("POST", body)
//				.addHeader("authority", "www.4devs.com.br")
//				.addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36")
//				.addHeader("content-type", "application/x-www-form-urlencoded")
//				.addHeader("accept", "*/*")
//				.addHeader("origin", "https://www.4devs.com.br")
//				.addHeader("sec-fetch-site", "same-origin")
//				.addHeader("sec-fetch-mode", "cors")
//				.addHeader("sec-fetch-dest", "empty")
//				.addHeader("referer", "https://www.4devs.com.br/gerador_de_pessoas")
//				.addHeader("accept-language", "en-US,en;q=0.9")
//				.addHeader("cookie", "_ga=GA1.3.1434346808.1591717821; __gads=ID=3f3a9a6c4be300f5:T=1591717821:S=ALNI_MagathFAMle8BIUw9wYGkdTAwHfWw; _gid=GA1.3.1611039306.1597497113")
//				.build();
//		Response response = client.newCall(request).execute();
//	}

	private void gerarPessoas() throws JsonProcessingException {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("authority", "www.4devs.com.br");
		headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36");
		headers.add("content-type", "application/x-www-form-urlencoded");
		headers.add("accept", "*/*");
		headers.add("origin", "https://www.4devs.com.br");
		headers.add("sec-fetch-site", "same-origin");
		headers.add("sec-fetch-mode", "cors");
		headers.add("sec-fetch-dest", "empty");
		headers.add("referer", "https://www.4devs.com.br/gerador_de_pessoas");
		headers.add("accept-language", "en-US,en;q=0.9");
		headers.add("cookie", "_ga=GA1.3.1434346808.1591717821; __gads=ID=3f3a9a6c4be300f5:T=1591717821:S=ALNI_MagathFAMle8BIUw9wYGkdTAwHfWw; _gid=GA1.3.1611039306.1597497113");

		HttpEntity<String> entity = new HttpEntity<>("acao=gerar_pessoa&sexo=I&pontuacao=S&idade=0&cep_estado=&txt_qtde=30&cep_cidade=", headers);
		final ResponseEntity<String> result = restTemplate.postForEntity("https://www.4devs.com.br/ferramentas_online.php", entity, String.class);
		JSONArray jsonArray = new JSONArray(result.getBody());
		for (int i = 0; i < 29; i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			ObjectMapper objectMapper = new ObjectMapper();
			final PessoaFisica pessoaFisica = objectMapper.readValue(jsonObject.toString(), PessoaFisica.class);
			System.err.println(pessoaFisica.toString());
			repositoryPessoaFisica.save(pessoaFisica);
		}


	}
}
