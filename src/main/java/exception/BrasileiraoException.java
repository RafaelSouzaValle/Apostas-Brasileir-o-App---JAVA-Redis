package exception;

	/**
	 * Responável pelas exceções da aplicação
	 * 
	 * @author Rafael.Valle
	 *
	 */
	@SuppressWarnings("serial")
	public class BrasileiraoException extends Exception {

		/**
		 * Retorna mensagem com detalhe da exceção
		 * @param message
		 */
		public BrasileiraoException(String message) {
			super(message);
		}
	}

