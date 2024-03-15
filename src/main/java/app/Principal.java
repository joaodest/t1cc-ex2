package app;

import dao.CryptoDao;
import model.Crypto;
import service.CryptoService;
import static spark.Spark.*;
import com.google.gson.Gson;

import static spark.Spark.*;

public class Principal {

	public static void main(String[] args) {
		CryptoDao cryptoDAO = new CryptoDao();
		CryptoService cryptoService = new CryptoService(cryptoDAO);
		Gson gson = new Gson();

		port(6789);

		get("/cryptos", (req, res) -> {
			res.type("application/json");
			return gson.toJson(cryptoService.getAllCrypto());
		});

		get("/cryptos/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			res.type("application/json");
			return gson.toJson(cryptoService.getCrypto(id));
		});

		post("/cryptos", (req, res) -> {
            Crypto crypto = gson.fromJson(req.body(), Crypto.class);
            cryptoService.addCrypto(crypto);
            res.status(201); 
            return "Ativo adicionada com sucesso.";
        });

		put("/cryptos/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			Crypto crypto = gson.fromJson(req.body(), Crypto.class);
			cryptoService.updateCrypto(crypto, id);
			return "Ativo atualizado com sucesso!";
		});

		delete("/cryptos/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			cryptoService.deleteCrypto(id);
			return "Ativo removido com sucesso!";
		});
	}
}
