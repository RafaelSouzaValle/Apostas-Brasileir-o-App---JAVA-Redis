package exception;

	/**
	 * Respon�vel pelas exce��es da aplica��o
	 * 
	 * @author Rafael.Valle
	 *
	 */
	@SuppressWarnings("serial")
	public class BrasileiraoException extends Exception {

		/**
		 * Retorna mensagem com detalhe da exce��o
		 * @param message
		 */
		public BrasileiraoException(String message) {
			super(message);
		}
	}

