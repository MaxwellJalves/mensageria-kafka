package br.com.recapitulando;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class ListandoPropriedades {

    private static final String ARQUIVO_DE_CONFIGURACAO ="src/main/resources/dev.properties";

    private static final Logger LOG = Logger.getAnonymousLogger() ;

    public static void main(String[] args)  {
        Properties props = new Properties();
        try{
            props.load(new FileInputStream(ARQUIVO_DE_CONFIGURACAO));

            for(var valores : props.entrySet()){
                LOG.info(String.format("Chave : %s  Valor: %s \n",valores.getKey(),valores.getValue()));
            }
        }catch (IOException e){
            LOG.info(e.getCause().getMessage());

        }

    }
}
