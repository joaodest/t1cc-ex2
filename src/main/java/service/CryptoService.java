package service;

import java.util.ArrayList;
import java.util.List;

import dao.CryptoDao;
import model.Crypto;

public class CryptoService implements ICryptoService {
	private CryptoDao cryptoDao = new CryptoDao();

	public CryptoService(CryptoDao cryptoDao) {
		super();
		this.cryptoDao = cryptoDao;
	}

	@Override
	public void addCrypto(Crypto crypto) {
		cryptoDao.insert(crypto);
	}

	@Override
	public Crypto getCrypto(int id) {
		return cryptoDao.getById(id);
	}

	@Override
	public List<Crypto> getAllCrypto() {
		List<Crypto> cryptoList = new ArrayList<>();

		cryptoList = cryptoDao.getAllCrypto();

		return cryptoList;
	}

	@Override
	public void updateCrypto(Crypto crypto, int id) {
		Crypto cryptoToUpdate = cryptoDao.getById(id);

		if (cryptoToUpdate != null) {
			cryptoToUpdate.setPrice(crypto.getPrice());
			cryptoDao.update(cryptoToUpdate);
		} else {
			System.out.println("Cryptocurrencie não encontrada: " + id);
		}

	}

	@Override
	public void deleteCrypto(int id) {
		cryptoDao.delete(id);
	}

}
