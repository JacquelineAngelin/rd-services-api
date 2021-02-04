package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.Cardapio;
import com.rd.projetointegrador.rdservicesapi.dto.TipoRefeicao;
import com.rd.projetointegrador.rdservicesapi.entity.CardapioEntity;
import com.rd.projetointegrador.rdservicesapi.entity.TipoRefeEntity;
import com.rd.projetointegrador.rdservicesapi.repository.CardapioRepository;
import com.rd.projetointegrador.rdservicesapi.repository.TipoRefeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TipoRefeicaoService {
    @Autowired
    private CardapioRepository cardapioRepository;

    @Autowired
    private TipoRefeRepository tipoRefeRepository;


    public Map<TipoRefeicao, List<Cardapio>> listarRefeicoes(){

        List<CardapioEntity> lista = cardapioRepository.getByIdPaciente(BigInteger.valueOf(24l));

        Map<TipoRefeEntity, List<CardapioEntity>> mapCardapios = new HashMap<TipoRefeEntity, List<CardapioEntity>>();

        for(CardapioEntity entity : lista) {

            TipoRefeEntity tipoRefeEntity = entity.getIdTipoRefeicao();
            List<CardapioEntity> listaCardapio;

            if (mapCardapios.containsKey(tipoRefeEntity)){
              listaCardapio = mapCardapios.get(entity.getIdTipoRefeicao());
            } else {
               listaCardapio = new ArrayList<CardapioEntity>();
            }
            listaCardapio.add(entity);
            mapCardapios.put(entity.getIdTipoRefeicao(), listaCardapio);
        }

        Map<TipoRefeicao, List<Cardapio>> mapCardapioResul = new HashMap<TipoRefeicao, List<Cardapio>>();

        for(Map.Entry<TipoRefeEntity, List<CardapioEntity>> entrada : mapCardapios.entrySet()){

            List<Cardapio> listaCardapio = new ArrayList<>();
            TipoRefeicao t = new TipoRefeicao();
            t.setDsTipoRefeicao(entrada.getKey().getDsTipoRefeicao());
            t.setIdTipoRefeicao(entrada.getKey().getIdTipoRefeicao());

            for(CardapioEntity cardapio: entrada.getValue()){
                Cardapio c = new Cardapio();

                c.setIdMedico(cardapio.getIdMedico());
                c.setIdPaciente(cardapio.getIdPaciente());
                c.setIdTipoRefeicao(t);
                c.setDsDescricao(cardapio.getDsDescricao());
                c.setNomeReceita(cardapio.getNomeReceita());
                c.setQtRendimento(cardapio.getQtRendimento());
                c.setQtCalorias(cardapio.getQtCalorias());

                listaCardapio.add(c);
            }

            mapCardapioResul.put(t,listaCardapio);

        }
        return mapCardapioResul;
    }
}