package com.soulcode.chamaelas.ChamaElas.services;

import com.soulcode.chamaelas.ChamaElas.models.FuncaoModel;
import com.soulcode.chamaelas.ChamaElas.models.UsuarioModel;
import com.soulcode.chamaelas.ChamaElas.models.dto.FuncaoDTO;
import com.soulcode.chamaelas.ChamaElas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;


@Service
public class AutenticacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Verificação se o email já existe na base de dados
    public String verifiqueSeOEmailJaFoiCadastrado(String email, Model model) {
        Optional<UsuarioModel> usuarioExistenteOptional = usuarioRepository.findByEmail(email);
        if (usuarioExistenteOptional.isPresent()) {
            model.addAttribute("error", "Este e-mail já está em uso. Por favor, escolha outro.");
        }
        return "cadastro-usuario";
    }
    // Verificação para confirmar a senha
    public String verifiqueSeAsSenhasSaoIguais(String senha, String confirmacaoSenha, Model model) {
        if (!senha.equals(confirmacaoSenha)) {
            model.addAttribute("error", "As senhas não correspondem.");
        }
        return "cadastro-usuario"; // ALTERAR PARA A PÁGINA CORRESPONDENTE
    }

    // Atribui uma função (role) do usuário cadastrado
    public FuncaoModel atribuiFuncaoAoUsuario(String funcao) {
        FuncaoDTO roleDTO;

        if (funcao.equals("tecnico")) {
            roleDTO = new FuncaoDTO(1L, "Técnico");
        } else {
            roleDTO = new FuncaoDTO(2L, "Cliente");
        }
        return FuncaoDTO.toModel(roleDTO);
    }
}