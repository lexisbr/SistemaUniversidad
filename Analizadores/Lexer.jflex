package Analizadores;
import Analizadores.Token;
import Analizadores.sym;
import java_cup.runtime.Symbol;
import static Analizadores.sym.*;

%%

%public 
%class Lexer
%cup
%cupdebug
%line
%column

//Espacios en blanco
SEPARADOR = \r|\r\n|\n
ESPACIO = {SEPARADOR} | [ \t\f]

NUMERO = [0-9]+
ID = [:jletterdigit:]+

STRING_WS = "\""[^["\""][ \r\t\b\f\n]]+"\""
STRING_S = "\""[^["\""]]+"\""

USUARIO = "Usuario"
ESTUDIANTE = "Estudiante"
USUARIO = "Usuario"
CATEDRATICO = "Catedratico"
EDIFICIO = "Edificio"
SALON = "Salon"
CURSO = "Curso"
HORARIO = "Horario"
ASIGNAR = "Asignar"
PARENTESIS_A = "("
PARENTESIS_C = ")"
COMA = ","
PUNTOCOMA = ";"


%{
    private Symbol symbol(int type){
        String lexeme = yytext();
        System.out.printf("Token >> Tipo: %d | Lexeme: %s | Linea: %d | Columna: %d\n", type, lexeme == null ? "" : lexeme, yyline + 1, yycolumn + 1);
        return new Symbol(type, new Token(lexeme, yyline+1,yycolumn+1));
    }

%}


%%

<YYINITIAL> {

    {ESTUDIANTE} { return symbol(ESTUDIANTE); }

    {USUARIO} { return symbol(USUARIO); }

    {CATEDRATICO} { return symbol(CATEDRATICO); }
    
    {EDIFICIO} { return symbol(EDIFICIO); }

    {SALON} { return symbol(SALON); }

    {CURSO} { return symbol(CURSO); }

    {HORARIO} { return symbol(HORARIO); }

    {ASIGNAR} { return symbol(ASIGNAR); }

    {PARENTESIS_A} { return symbol(PARENTESIS_A); }

    {PARENTESIS_C} { return symbol(PARENTESIS_C); }

    {COMA} { return symbol(COMA); }

    {PUNTOCOMA} { return symbol(PUNTOCOMA); }

    {NUMERO} { return symbol(NUMERO); }

    {STRING_S} { return symbol(STRING_S); }

    {ID} { return symbol(ID); }

    {ESPACIO} { }   
}

[^]
{
    System.out.println("Error en "+yytext());
}