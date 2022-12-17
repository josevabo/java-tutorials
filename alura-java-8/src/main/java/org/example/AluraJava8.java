package org.example;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class AluraJava8
{
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        List<String> palavras = new ArrayList<>();
        String palavra = "avc";

        palavras.add("alura online");
        palavras.add("casa do código");
        palavras.add("caelum");
        palavras.forEach(new Consumer<String>(){
            public void accept(String palavra) {
                System.out.println(palavra);
            }
        });
        palavras.forEach(s -> System.out.println(s));

        var comparador = new ComparadorPorTamanho();
        palavras.sort(comparador);

        System.out.println(palavras);

        System.out.println("03  Escrevendo o forEach com lambda\n");
        palavras.forEach(s -> System.out.println(s));

        // Método sort com lambda
        System.out.println("Método sort com lambda");
        palavras.sort(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                if(s1.length() < s2.length())
                    return -1;
                if(s1.length() > s2.length())
                    return 1;
                return 0;
            }
        });

        palavras.sort((s1,s2) -> {
            if(s1.length() < s2.length())
                return -1;
            if(s1.length() > s2.length())
                return 1;
            return 0;
        });

        // Melhorandoainda mais
        palavras.sort((s1,s2) -> Integer.compare(s1.length(), s2.length()));

        palavras.sort((s1,s2) -> s1.length() - s2.length());

        //Entendendo o tipo de um lambda
//         Object o = s -> System.out.println(s); //java: incompatible types: java.lang.Object is not a functional interface
        CustomFunctionalInterface o = s -> System.out.println(s);
        System.out.println("Printando consumer");
        Consumer a = s -> System.out.println(s);

        palavras.forEach(a);

//        Threads com lambda
        System.out.println("Threads com lambda");
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("rodou o run");
            }
        }).run();

        // com lambda
        new Thread(() -> System.out.println("rodou o run com lambda")).run();

//        Código mais sucinto com Method references
        System.out.println("Código mais sucinto com Method references\n");
        palavras.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));
        palavras.sort(Comparator.comparing(s -> s.length()));
        Comparator<String> comparator = Comparator.comparing(s -> s.length());
        palavras.sort(comparator);
        Function<String, Integer> funcao = s -> s.length();
        Comparator<String> comparator2 = Comparator.comparing(funcao);
        palavras.sort(comparator2);
        // Method Reference
        palavras.sort(Comparator.comparing(String::length));
        palavras.forEach(String::length);
        palavras.sort(Comparator.comparing(s -> s.length()));
        System.out.println("comparando com hashcode");
        palavras.sort(Comparator.comparing(String::hashCode));
        System.out.println(palavras);
        palavras.forEach(System.out::println);

        // Streams: trabalhando melhor com coleções
        System.out.println("Streams: trabalhando melhor com coleções\n");

        List<Curso> cursos = new ArrayList<Curso>();
        cursos.add(new Curso("Python", 45));
        cursos.add(new Curso("JavaScript", 150));
        cursos.add(new Curso("Java 8", 113));
        cursos.add(new Curso("C", 55));
        cursos.sort(Comparator.comparingInt(c -> c.getAlunos()));
        cursos.sort(Comparator.comparingInt(Curso::getAlunos));
        // streams
        Stream<Curso> streamDeCurso = cursos.stream();
        streamDeCurso = cursos.stream().filter(c -> c.getAlunos() > 100);
        cursos.forEach(c -> System.out.println(c.getNome()));
        cursos.stream()
                .filter(c -> c.getAlunos() > 100)
                .forEach(c -> System.out.println(c.getNome()));
        cursos.stream()
                .filter(c -> c.getAlunos() > 100)
                .map(c -> c.getAlunos());

        cursos.stream()
                .filter(c -> c.getAlunos() > 100)
                .map(c -> c.getAlunos())
                .forEach(x -> System.out.println(x));
        cursos.stream()
                .filter(c -> c.getAlunos() > 100)
                .map(Curso::getAlunos)
                .forEach(System.out::println);

        IntStream stream = cursos.stream()
                .filter(c -> c.getAlunos() > 100)
                .mapToInt(Curso::getAlunos);

        int soma = cursos.stream()
                .filter(c -> c.getAlunos() > 100)
                .mapToInt(Curso::getAlunos)
                .sum();

        // Exercicios
        //Utilizando a API de Stream, crie um filtro para todos os cursos que tenham mais de 50 alunos.
        System.out.println("Cursos com mais de 50 alunos");
        cursos.stream()
                .filter(c -> c.getAlunos() > 50)
                .map(Curso::getNome)
                .forEach(System.out::println);

        // Mais Streams, Collectors e APIs
        System.out.println("Mais Streams, Collectors e APIs\n");

        Optional<Curso> optional = cursos.stream()
                .filter(c -> c.getAlunos() > 100)
                .findAny();
        Curso curso = optional.get();
        curso = optional.orElse(null);
        optional.ifPresent(Curso::getNome);

        cursos.stream()
                .filter(c -> c.getAlunos() > 100)
                .findAny()
                .ifPresent(Curso::getNome);
        cursos.stream()
                .filter(c -> c.getAlunos() > 100)
                .findFirst()
                .ifPresent(Curso::getNome);

        cursos.stream()
                .filter(c -> c.getAlunos() > 100)
                .collect(Collectors.counting());

        System.out.println("Calcule a quantidade média de alunos de todos os seus cursos utilizando a API de Stream.");
        System.out.println(cursos.stream()
//                .map()
                .mapToInt(Curso::getAlunos)
                .average());

        Stream<Curso> streamMaiorCinquenta = cursos.stream()
                .filter(c -> c.getAlunos() > 50);
        System.out.println("Como podemos transformar esse Stream<Curso> filtrado em uma List<Curso>?");
        streamMaiorCinquenta.collect(Collectors.toList());


        //A nova API de datas
        System.out.println("Nova API de DAtas");
        LocalDate olimpiadasRio = LocalDate.of(2016, Month.JUNE, 5);
        LocalDate hoje = LocalDate.now();
        int anos = olimpiadasRio.getYear() - hoje.getYear();
        System.out.println(anos);
        Period periodo = Period.between(hoje, olimpiadasRio);
        System.out.println(periodo);
        periodo = Period.between(hoje, olimpiadasRio);
        System.out.println(periodo.getYears());
        System.out.println(periodo.getMonths());
        System.out.println(periodo.getDays());
        String stringTeste ="Teste Imutavel";
        stringTeste = "imutavel mesmo";

        System.out.println(hoje.minusYears(1));
        System.out.println(hoje.minusMonths(4));
        System.out.println(hoje.minusDays(2));

        System.out.println(hoje.plusYears(1));
        System.out.println(hoje.plusMonths(4));
        System.out.println(hoje.plusDays(2));

        //Uma API imutável
        System.out.println("Uma API imutável\n");
        olimpiadasRio.plusYears(4);
        System.out.println(olimpiadasRio);
        LocalDate proximasOlimpiadas = olimpiadasRio.plusYears(4);
        System.out.println(proximasOlimpiadas);

        //Formatando suas datas
        System.out.println("Formatando suas datas");
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String valorFormatado = proximasOlimpiadas.format(formatador);
        System.out.println(valorFormatado);

        DateTimeFormatter formatadorComHoras = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
        LocalDateTime agora = LocalDateTime.now();
        System.out.println(agora.format(formatadorComHoras));

        YearMonth anoEMes = YearMonth.of(2015, Month.JANUARY);

        LocalTime intervalo = LocalTime.of(12, 30);
        System.out.println(intervalo);

        System.out.println(LocalDateTime.of(2022,Month.DECEMBER, 17, 12, 24));

        ZonedDateTime horaComZona = ZonedDateTime.now();

        System.out.println(horaComZona);

        horaComZona = ZonedDateTime.of(agora, ZoneId.of("GMT"));
        System.out.println(horaComZona);
//        horaComZona = ZonedDateTime.of(agora, ZoneId.of("EST"));
//        System.out.println(horaComZona);
        horaComZona = ZonedDateTime.of(agora, ZoneId.of("+6"));
        System.out.println(horaComZona);

        LocalDate data = LocalDate.of(2099,1,5);
        System.out.println(data);
        data = LocalDate.of(2099,Month.JANUARY,5);
        System.out.println(data);

        LocalDateTime antes = LocalDateTime.of(2022,10,01,12,00);
        LocalDateTime depois = LocalDateTime.of(2023,8,5,10,00);

        Period periodo2 = Period.between(depois.toLocalDate(), antes.toLocalDate());
        System.out.println(periodo2);
        periodo2 = Period.between(antes.toLocalDate(), depois.toLocalDate());
        System.out.println(periodo2);
        periodo2 = Period.between(antes.toLocalDate(), antes.toLocalDate());
        System.out.println(periodo2);
        Arrays.asList(periodo2.getClass().getDeclaredFields()).forEach(System.out::println);
//        periodo2.getClass().getDeclaredField("days").setAccessible(true);//setAccessible(true);
//        periodo2.getClass().getDeclaredField("days").set(periodo2.getDays(), 15);//setAccessible(true);
//        System.out.println(periodo2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println(antes.format(formatter));


    }


}
class ComparadorPorTamanho implements Comparator<String> {

    @Override
    public int compare(String s1, String s2) {
        if (s1.length() < s2.length())
            return -1;
        if (s1.length() > s2.length())
            return 1;
        return 0;
    }
}

@FunctionalInterface
interface CustomFunctionalInterface {
    public void execute(String target);
}

class Curso {
    private String nome;
    private int alunos;

    public Curso(String nome, int alunos) {
        this.nome = nome;
        this.alunos = alunos;
    }

    public String getNome() {
        return nome;
    }

    public int getAlunos() {
        return alunos;
    }
}