/*
*  CRUD DE CONTATO
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HospedeDAO {

    //armazena o obj de conexão com o BD mySql
    private Connection connection;
    //obj stmt que executa as consultas no BD
    private PreparedStatement stmt;

    public HospedeDAO() {
        //inicializa a conexão com o BD
        this.connection = ConnectionFactory.getConnection();
    }

    public void cadastrar(HospedeBEAN c) {
        String sql = "insert into hospede (hosNome, hosEndereco, hosTelefone, hosCPF, hosUF, hosBairro,hosCidade, hosOrgaoEmissor, hosCelular, hosCEP, hosDataNascimento) values (?, ?, ?, ?, ?,?,?,?,?,?,?);";

        try {
            //prepared statement para inserção
            stmt = connection.prepareStatement(sql);

            //seta os valores
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getEndereco());
            stmt.setString(3, c.getTelefone());
            stmt.setString(4, c.getCPF());
            stmt.setString(5, c.getUF());
            stmt.setString(6, c.getBairro());
            stmt.setString(7, c.getCidade());
            stmt.setString(8, c.getOrgaoEmissor());
            stmt.setString(9, c.getCelular());
            stmt.setString(10, c.getCEP());
            stmt.setString(11, c.getDataNascimento());

            //executa
            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<HospedeBEAN> listarALL() {
        String sql = "select * from hospede;";
        ArrayList<HospedeBEAN> contatoAL = new ArrayList<>();
        try {
            stmt = connection.prepareStatement(sql);

            //executa
            ResultSet rs = stmt.executeQuery();
            //joga resultado da consulta no arrayList
            while (rs.next()) {
                HospedeBEAN c = new HospedeBEAN();
                c.setCod(rs.getInt(1));
                c.setNome(rs.getString(2));
                c.setEndereco(rs.getString(3));
                c.setTelefone(rs.getString(4));
                c.setCPF(rs.getString(5));
                c.setUF(rs.getString(6));
                c.setBairro(rs.getString(7));
                c.setCidade(rs.getString(8));
                c.setOrgaoEmissor(rs.getString(9));
                c.setCelular(rs.getString(10));
                c.setCEP(rs.getString(11));
                c.setDataNascimento(rs.getString(12));

                //adiciona os dados no array
                contatoAL.add(c);
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contatoAL;
    }

    public HospedeBEAN localizar(int codigo) {
        String sql = "select * from hospede where hosCodigo = ?;";
        HospedeBEAN c = new HospedeBEAN();
        try {
            //prepared statement para seleção
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, codigo); // passo ele para consulta SQL

            //executa
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                //seta os valores
                c.setCod(rs.getInt(1));
                c.setNome(rs.getString(2));
                c.setEndereco(rs.getString(3));
                c.setTelefone(rs.getString(4));
                c.setCPF(rs.getString(5));
                c.setUF(rs.getString(6));
                c.setBairro(rs.getString(7));
                c.setCidade(rs.getString(8));
                c.setOrgaoEmissor(rs.getString(9));
                c.setCelular(rs.getString(10));
                c.setCEP(rs.getString(11));
                c.setDataNascimento(rs.getString(12));

                //contatoAL.add(c);
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return c;

    }

    public boolean editar(HospedeBEAN c) {
        String sql = "update hospede set hosNome = ?, hosEndereco = ?, hosTelefone = ?,hosCPF= ?,hosUF= ?,hosBairro= ?,hosCidade=?,hosOrgaoEmissor= ?, hosCelular= ?,hosDataNascimento =?, hosCEP = ? where hosCodigo = ?;";
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getEndereco());
            stmt.setString(3, c.getTelefone());
            stmt.setString(4, c.getCPF());
            stmt.setString(5, c.getUF());
            stmt.setString(6, c.getBairro());
            stmt.setString(7, c.getCidade());
            stmt.setString(8, c.getOrgaoEmissor());
            stmt.setString(9, c.getCelular());
            stmt.setString(10, c.getDataNascimento());
            stmt.setString(11, c.getCEP());

            stmt.setInt(12, c.getCod());

            int linhasAtualizadas = stmt.executeUpdate();
            stmt.close();
            if (linhasAtualizadas > 0) {
                System.out.println("Foram alterados: " + linhasAtualizadas);
            }
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean remover(int codigo) {
        String sql = "delete from hospede where hoscodigo = ?;";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, codigo);
            //executa
            stmt.execute();
            stmt.close();
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
