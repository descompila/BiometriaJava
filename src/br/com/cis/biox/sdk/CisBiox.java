/*
 * To change sdk license header, choose License Headers in Project Properties.
 * To change sdk template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cis.biox.sdk;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

/**
 *
 * @author Samuelson Brito
 * <b>Email:</b> samuelsonbrito@outlook.com
 */
public class CisBiox {

    CisBioxSDK sdk = CisBioxSDK.getInstance();

    private Pointer digital;
    private int resultado;

    public byte[] capturarDigital() {
        IntByReference iRet = new IntByReference();
        this.digital = this.lerDigitalRetornoPonteiro(iRet);
        this.resultado = iRet.getValue();
        return digital.getByteArray(0, 669);
    }

    /**
     * @return (int) resposta SDK
     */
    public int iniciar() {
        int iRetorno = sdk.CIS_SDK_Biometrico_Iniciar();
        this.resultado = iRetorno;
        return iRetorno;
    }

    /**
     * @return (int)
     */
    public int finalizar() {
        int iRetorno = sdk.CIS_SDK_Biometrico_Finalizar();
        return iRetorno;
    }

    /**
     * @param pTemplate = template da digital
     * @return (int)
     */
    public int lerDigital(PointerByReference pTemplate) {
        int iRetorno = sdk.CIS_SDK_Biometrico_LerDigital(pTemplate);
        return iRetorno;
    }

    /**
     * @param iRetorno
     * @return (Pointer)
     */
    public Pointer lerDigitalRetornoPonteiro(IntByReference iRetorno) {
        Pointer pDigital;
        pDigital = sdk.CIS_SDK_Biometrico_LerDigital_RetornoPonteiro(iRetorno);
        return pDigital;
    }

    /**
     * @return (int)
     */
    public int cancelarLeitura() {
        int iRetorno = sdk.CIS_SDK_Biometrico_CancelarLeitura();
        return iRetorno;
    }

    /**
     * @param pDigital1
     * @param pDigital2
     * @return (int)
     */
    public int compararDigital(byte[] pDigital1, byte[] pDigital2) {
        int iRetorno = sdk.CIS_SDK_Biometrico_CompararDigital(preparaDigital(pDigital1), preparaDigital(pDigital2));
        return iRetorno;
    }

    /**
     * @param iRetorno
     * @param iSize
     * @return (Pointer)
     */
    public Pointer lerDigitalLerWSQ(IntByReference iRetorno, IntByReference iSize) {
        Pointer pImagem;
        pImagem = sdk.CIS_SDK_Biometrico_LerDigital_LerWSQ(iRetorno, iSize);
        return pImagem;
    }

    /**
     * @return (String)
     */
    public String versao() {
        Pointer p;
        p = sdk.CIS_SDK_Versao();
        return p.getString(0);
    }

    public static String mensagens(int iRetorno) {

        String msg = "";

        switch (iRetorno) {
            case 0:
                msg = "COMANDO NÃO EXECUTADO!";
                break;
            case 1:
                msg = "COMANDO EXECUTADO COM SUCESSO";
                break;
            case -1:
                msg = "LEITOR INCOMPATIVEL COM SDK";
                break;
            case -2:
                msg = "DIGITAIS NÃO SÃO IGUAIS";
                break;
            case -10:
                msg = "ERRO DESCONHECIDO";
                break;
            case -11:
                msg = "FALTA DE MEMORIA";
                break;
            case -12:
                msg = "ARGUMENTO INVALIDO";
                break;
            case -13:
                msg = "LEITOR EM USO";
                break;
            case -14:
                msg = "TEMPLATE INVALIDO";
                break;
            case -15:
                msg = "ERRO INTERNO";
                break;
            case -16:
                msg = "NAO HABILITADO PARA CAPTURAR DIGITAL";
                break;
            case -17:
                msg = "CANCELADO PELO USUARIO";
                break;
            case -18:
                msg = "LEITURA NÃO POSSIVEL";
                break;
            case -21:
                msg = "ERRO DESCONHECIDO";
                break;
            case -22:
                msg = "SDK NÃO FOI INICIADO";
                break;
            case -23:
                msg = "LEITOR NÃO CONECTADO";
                break;
            case -24:
                msg = "ERRO NO LEITOR";
                break;
            case -25:
                msg = "FRAME DE DADOS VAZIO";
                break;
            case -26:
                msg = "ORIGEM FALSA (FAKE)";
                break;
            case -27:
                msg = "HARDWARE INCOMPATIVEL";
                break;
            case -28:
                msg = "FIRMWARE INCOMPATIVEL";
                break;
            case -29:
                msg = "FRAME ALTERADO";
                break;
            default:
                break;
        }

        return msg;
    }

    private PointerByReference preparaDigital(byte[] buffer) {
        final Pointer p = new Memory(669);
        p.write(0, buffer, 0, buffer.length);
        final PointerByReference pr = new PointerByReference();
        pr.setPointer(p);
        return pr;
    }

    public int getResultado() {
        return resultado;
    }
    
    

}
