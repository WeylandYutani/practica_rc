import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

class RNBPv4Cliente {
	
	static Scanner entrada_teclado = new Scanner(System.in);
	
	public static void main(String argv[]) throws Exception 
	{
	//comentario
		BufferedReader entradaDesdeUsuario = new BufferedReader(new InputStreamReader(System.in)); 

	    Socket socketCliente = new Socket("serverrc.af.lab", 56794);
	    System.out.println("Se ha establecido conexion TCP con el servidor."); 
		
	    DataOutputStream salidaAServidor = new DataOutputStream(socketCliente.getOutputStream()); 

	    BufferedReader entradaDesdeServidor = new BufferedReader(new InputStreamReader(socketCliente.getInputStream())); 
	    
		int opcion = 0;
		int opcion_2 = 0;
		int menu_1 = 0;
		int menu_2 = 0;
		String frase;
		String fraseModificada;
		String[] num;
		String num2 = "";
		Integer num3 = 0;
		String cadena = "";
		int num4;
		char B;
		String cadenaEnviada="";
		int tamanioCadenaEnviada = 0;
		String usuario = "";
		String contrasenia = "";
		int indice = 0;
		
		do{
			System.out.print("Menu---------------------\n\t");
			System.out.print("1. Autenticar\n\t");
			System.out.print("2. Salir");
			
			opcion = entrada_teclado.nextInt();
			
			switch(opcion)
			{
				case 1: 
						menu_1 = 1;
						System.out.println("Teclee su usuario:");
						usuario = entradaDesdeUsuario.readLine(); 
						System.out.println("Teclee su contraseña:");
						contrasenia = entradaDesdeUsuario.readLine(); 
					    frase = "aut " + usuario + " " + contrasenia; 
					    salidaAServidor.writeBytes(frase + '\n');
					    fraseModificada = entradaDesdeServidor.readLine();
						num =  fraseModificada.split(" ");
						num2 = num[2];
						num3 = Integer.parseInt(num2);
						cadena = "";
						for(int i=0; i<=num3-1; i++)
						{
							num4 = entradaDesdeServidor.read();
							B = (char)num4;
							cadena += B;
						}
		
					    System.out.println("Respuesta del servidor: " + fraseModificada + " " + cadena); 
					    
					    do{
							System.out.print("Menu---------------------\n\t"+
											"1. Solicitar Bloc de Notas\n\t"
											+ "2. Borrar Bloc de Notas\n\t"
											+ "3. Modificar Bloc de Notas\n\t"
											+ "4. Salir");
							
							opcion_2 = entrada_teclado.nextInt();
							
							switch(opcion_2)
							{
								case 1: 
										menu_2 = 0;
									    frase = "get"; 
									    salidaAServidor.writeBytes(frase + '\n');
									    fraseModificada = entradaDesdeServidor.readLine();
									    //System.out.println("Contenido frase modificada" + fraseModificada);
									    num =  fraseModificada.split(" ");
									    indice = num.length;
									    //System.out.println("El indice es " + indice);
										num2 = num[indice-1];
										//System.out.println("Contenido del array" + Arrays.toString(num));
										num3 = Integer.parseInt(num2);
										cadena = "";
										for(int i=0; i<=num3-1; i++)
										{
											num4 = entradaDesdeServidor.read();
											B = (char)num4;
											cadena += B;
										}
									    System.out.println("Respuesta del servidor: " + fraseModificada + " " +cadena); 
									    
									break;
									
								case 2: 
									 
									frase = "put " + 0; 
								    salidaAServidor.writeBytes(frase + '\n');
								    fraseModificada = entradaDesdeServidor.readLine();
									//System.out.println("Contenido frase modificada" + fraseModificada);
								    num =  fraseModificada.split(" ");
								    indice = num.length;
								    //System.out.println("El indice es " + indice);
									num2 = num[indice-1];
									//System.out.println("Contenido del array" + Arrays.toString(num));
									num3 = Integer.parseInt(num2);
									cadena = "";
									for(int i=0; i<=num3-1; i++)
									{
										num4 = entradaDesdeServidor.read();
										B = (char)num4;
										cadena += B;
									}
								    System.out.println("Respuesta del servidor: " + fraseModificada + " " +cadena); 
									break;
									
								case 3: 
									
									System.out.println("Teclee la frase a enviar al bloc: ");
									cadenaEnviada = entradaDesdeUsuario.readLine(); 
									tamanioCadenaEnviada = cadenaEnviada.length();
									frase = "put " + tamanioCadenaEnviada; 
								    salidaAServidor.writeBytes(frase + '\n' + cadenaEnviada);
								    fraseModificada = entradaDesdeServidor.readLine();
								  //System.out.println("Contenido frase modificada" + fraseModificada);
								    num =  fraseModificada.split(" ");
								    indice = num.length;
								    //System.out.println("El indice es " + indice);
									num2 = num[indice-1];
									//System.out.println("Contenido del array" + Arrays.toString(num));
									num3 = Integer.parseInt(num2);
									cadena = "";
									for(int i=0; i<=num3-1; i++)
									{
										num4 = entradaDesdeServidor.read();
										B = (char)num4;
										cadena += B;
									}
								    System.out.println("Respuesta del servidor: " + fraseModificada + " " +cadena); 
									break;
									
								case 4: 
									menu_2 = 1;
									menu_1 = 0;
									break;
							}
							
						}while(menu_2 == 0);

					    
					break;
					
				case 2: 
					
					socketCliente.close();
				    System.out.println("Cerramos la conexion TCP con el servidor."); 
				    menu_1 = 1;
					break;
			}
		}while(menu_1 == 0);
		
		System.out.println("Que tenga usted un buen dia"); 
	}
	
	

}

