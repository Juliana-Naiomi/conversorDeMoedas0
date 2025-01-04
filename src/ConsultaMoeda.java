import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaMoeda {
    private final Gson gson = new Gson();
    private final String BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private final String API_KEY = "b2626c8f23954d81394492af";
    private final String ARQUIVO_CONSULTAS = "consultas.json";

    public Conversao buscaConversor(String baseCode, String targetCode, double valor) {
        URI endereco = URI.create(BASE_URL + API_KEY + "/pair/" + baseCode + "/" + targetCode + "/" + valor);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(endereco)
                .build();

        try {
            HttpResponse<String> response = HttpClient
                    .newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            Conversao conversao = gson.fromJson(response.body(), Conversao.class);
            gravarConsulta(baseCode, targetCode, valor, conversao.conversion_result());
            return conversao;

        } catch (Exception e) {
            throw new RuntimeException("Cotação não realizada.");
        }
    }

    private void gravarConsulta(String baseCode, String targetCode, double valor, String resultado) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Consulta consulta = new Consulta(baseCode, targetCode, valor, resultado);
        try (FileWriter writer = new FileWriter(ARQUIVO_CONSULTAS, true)) {
            writer.write(gson.toJson(consulta) + "\n");
        } catch (IOException e) {
            System.err.println("Erro ao gravar consulta: " + e.getMessage());
        }
    }
}