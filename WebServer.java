import java.io.* ;
import java.net.* ;
import java.util.* ;
public final class WebServer
{
	public static void main(String arvg[]) throws Exception
	{
	// Ajustar o numero da porta.
		int port = 6789;
	// Estabelecer o socket de escuta.
		ServerSocket socketEscuta = new ServerSocket(port);  
	// Processar a requisição de serviço HTTP em um laço infinito.
		while(true){
		// Escutar requisição de conexão TCP.
            Socket socketConecta = socketEscuta.accept(); 
			//System.out.println("rodando");
            // Construir um objeto para processar a mensagem de requisição HTTP.
            HttpRequest request = new HttpRequest(socketConecta);
            // Criar um novo thread para processar a requisição.
            Thread thread = new Thread(request);
            //Iniciar o thread.
            thread.start();
		}
	}
}

final class HttpRequest implements Runnable{
	
    //HttpRequest request = new HttpRequest(socketConecta);
	final static String CRLF = "\r\n";
	Socket socket;
	// Construtor
	public HttpRequest(Socket socket) throws Exception
	{
		this.socket = socket;
	}
	
	// Implemente o método run() da interface Runnable.
	public void run(){
		try {
			processRequest();
		} catch (Exception e) {
			System.out.println(e);
		}

		
	}
	private void processRequest() throws Exception{
		// Obter uma referência para os trechos de entrada e saída do socket.
		InputStream is = socket.getInputStream();
		DataOutputStream os = new DataOutputStream(socket.getOutputStream());
		// Ajustar os filtros do trecho de entrada.
		BufferedReader br = 
	              new BufferedReader(new
	              InputStreamReader(is)); 
		
		// Obter a linha de requisição da mensagem de requisição HTTP.
		String requestLine = br.readLine();
		//  Exibir a linha de requisição.
		System.out.println();
		System.out.println(requestLine);

		// Obter e exibir as linhas de cabeçalho.
		String headerLine = null;
		while ((headerLine = br.readLine()).length() != 0) {
			System.out.println(headerLine);
		}
		
		//Parte B
		// Extrair o nome do arquivo a linha de requisição.
		StringTokenizer tokens = new StringTokenizer(requestLine);
		tokens.nextToken(); // pular o método, que deve ser “GET”
		String fileName = tokens.nextToken();
		// Acrescente um "." de modo que a requisição do arquivo esteja dentro do diretório atual.
		fileName = "." + fileName;

		// Abrir o arquivo requisitado.
		FileInputStream fis = null;
		Boolean fileExists = true;
		try {
			fis = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			fileExists = false;
		}
		
		// Construir a mensagem de resposta.
		String statusLine = null;
		String contentTypeLine = null;
		String entityBody = null;
		if (fileExists) {
<<<<<<< HEAD
			statusLine = "HTTP/1.1 200 OK" + CRLF;
			contentTypeLine = "Content-Type:" + 
			contentType(fileName) + CRLF;
		} else {
			statusLine = "HTTP/1.1 404 Not Found" + CRLF;
			contentTypeLine = "Content-Type:" + 
=======
			statusLine = CRLF;
			contentTypeLine = "Content-type: " + 
			contentType(fileName) + CRLF;
		} else {
			statusLine = "404 Not Found ";
			contentTypeLine = "Content-type: " + 
>>>>>>> b6cb61e242c50d7506e8eba076420248fee7cb3a
					contentType(fileName) + CRLF;
			entityBody = "<HTML>" +
				"<HEAD><TITTLE>Not Found</TITTLE></HEAD>" +
				"<BODY>Not Found</BODY></HTML>";
		}
		
		// Enviar a linha de status.
		os.writeBytes(statusLine);
		// Enviar a linha de tipo de conteúdo.
		os.writeBytes(contentTypeLine);
		// Enviar uma linha em branco para indicar o fim das linhas de cabeçalho.
		os.writeBytes(CRLF);

		// Enviar o corpo da entidade.
		if (fileExists) {
			sendBytes(fis, os);
			fis.close();
		} else {
			os.writeBytes(entityBody);
		}
		//fim parte B
		
		//Feche as cadeias e o socket
		os.close();
		br.close();
		socket.close();
		
	}
	
	private static void sendBytes(FileInputStream fis, OutputStream os)
			throws Exception
			{
				// Construir um buffer de 1K para comportar os bytes no caminho para o socket.
			byte[] buffer = new byte[1024];
				int bytes = 0;
				// Copiar o arquivo requisitado dentro da cadeia de saída do socket.
				while((bytes = fis.read(buffer)) != -1 ) {
					os.write(buffer, 0, bytes);
				}
			}

	private static String contentType(String fileName)
	{
		if(fileName.endsWith(".htm") || fileName.endsWith(".html")) {
			return "text/html";
		}
<<<<<<< HEAD
		if(fileName.endsWith(".gif") || fileName.endsWith(".GIF")) {
			return "image/gif";
		}
		if(fileName.endsWith(".jpg") || fileName.endsWith(".JPG")) {
=======
		if(fileName.endsWith(".gif")) {
			return "image/gif";
		}
		if(fileName.endsWith(".jpg")) {
>>>>>>> b6cb61e242c50d7506e8eba076420248fee7cb3a
			return "image/jpeg";
		}
		return "application/octet-stream";
	}

}

