/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.github.lanchonete.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import main.java.com.github.Lanchonete.model.Produto;

/**
 *
 * @author Diones Gomes
 */
public class CadastrarProdutoArquivo {

    private final File arquivo;
/**
 * Inicializando o construtor sem passar parâmetros.
 * @author Diones Gomes
 * 
 */
    public CadastrarProdutoArquivo() {
        arquivo = new File("Produto.bin");

        if (!arquivo.exists()) {

            try {
                arquivo.createNewFile();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Falha na conexão com arquivo", "Mensagem Erro", JOptionPane.ERROR_MESSAGE);
            }

        }

    }
 /**
  * 
  * @param produto parãmetro 
  * @return true se o usuário for cadastrado, false se não.
  * @throws FileNotFoundException
  * @throws IOException
  * @throws ClassNotFoundException 
  */
    public boolean Adicionar(Produto produto) throws FileNotFoundException, IOException, ClassNotFoundException {
        List<Produto> produtos;

        if (arquivo.length() > 0) {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(arquivo));

            produtos = (List<Produto>) in.readObject();
        } else {
            produtos = new ArrayList<>();
        }

        if (produtos.add(produto)) {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(arquivo));

            out.writeObject(produtos);
            out.close();

            return true;

        } else {
            return false;
        }

    }

    public Produto buscar(Integer codigo) throws IOException, ClassNotFoundException {

        if (arquivo.length() > 0) {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(arquivo));

            List<Produto> lista = (List<Produto>) in.readObject();
            for (Produto p : lista) {
                if (p.getCodigo()== codigo) { /*Verifica se o código passado é igual ao código do produto armazenado*/
                    return p;
                }
            }

        }
        return null;

    }

    public List<Produto> listar() throws IOException, ClassNotFoundException {

        if (arquivo.length() > 0) {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(arquivo));

            return (List<Produto>) in.readObject();
        } else {
            return new ArrayList<>();
        }

    }

    public boolean atualizar(Produto produto ,Integer codigo) throws IOException, ClassNotFoundException {

        List<Produto> produtos;

        if (arquivo.length() > 0) {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(arquivo));

            produtos = (List<Produto>) in.readObject();
        } else {
            produtos = new ArrayList<>();
        }

        for (int i = 0; i < produtos.size(); i++) {

            if (produtos.get(i).getCodigo() == codigo) {

                produtos.get(i).setCodigo(produto.getCodigo());
                produtos.get(i).setNome(produto.getNome());
                produtos.get(i).setDescricao(produto.getDescricao());
                produtos.get(i).setPreco(produto.getPreco());
                
                ObjectOutputStream out = new ObjectOutputStream(
                        new FileOutputStream(arquivo));
                out.writeObject(produtos);
                out.close();

                return true;

            }

        }
        return false;

    }

    public boolean deletar(Integer codigo) throws IOException, ClassNotFoundException {

        List<Produto> produtos;

        if (arquivo.length() > 0) {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(arquivo));

            produtos = (List<Produto>) in.readObject();
        } else {
            return false;
        }

        for (int i = 0; i < produtos.size(); i++) {

            if (produtos.get(i).getCodigo()== codigo) {
                produtos.remove(i);
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo));
                out.writeObject(produtos);
                out.close();
                return true;
            }

        }
        return false;

    }
}
