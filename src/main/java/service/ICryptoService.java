package service;

import java.util.List;

import model.Crypto;

public interface ICryptoService {
	void addCrypto(Crypto crypto);
	Crypto getCrypto(int id);
	List<Crypto> getAllCrypto();
	void updateCrypto(Crypto crypto, int id);
	void deleteCrypto(int id);
}
