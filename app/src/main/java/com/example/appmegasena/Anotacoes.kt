package com.example.appmegasena
/*
 PROPRIEDADES DO ANDROID

        SP = para fontes utilizamos a propriedade SP para colocar tamanhos
        textStyle = Bold (Negrito)
        inputType = Tipo de entrada que esperamos no Input de dados no Edit Text
        textAligment = Alinhamento do texto

        tools = Tag que só renderiza na edição do App mas não quando executar o app


  REGRAS E CONSELHOS
        1- sempre comece seu app com a lógica de validar possíveis falhas  para que o usuário não consiga burlar o app e gerar erros


   BANCO DE DADOS
        SharedPreferences = Tipo de dado utilizaado para guardar preferências dentro de um BD local com características simples
                            como uma String, Int, Boolean, Float por exemplo

        GetSharedPreferences = Função da AppComptActivity que é usada para resgatar os dados das preferencias cadastradas
                               Essa função recebe dois paramêtros : Nome do Banco , Contexto (modo que o banco atuará)

        apply( ) = Função responsável por salvar dados de forma assíncrona ou seja, sem bloquear a interface e sem emitir resposta de êxito do salvamento
        commit( ) = Inverso do Applu
        putString (key , value) = Adicione Strings por meio de uma chave e um valor onde a chave depois será associada ao valor
        getString( key , defvalue) = Resgate o valor pela chave declarada no PutString e adicione um valor default (Valor padrão ao iniciar)



 */
