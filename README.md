# 🏅 Simulacao de Triatlo

Simulação de uma prova de triatlo moderno com 25 atletas competindo em corrida, tiro ao alvo e ciclismo.

## 🎯 Funcionalidades

- 25 atletas competindo simultaneamente
- 3 etapas: corrida (3km), tiro ao alvo (3 disparos), ciclismo (5km)
- 5 armas disponíveis (recurso limitado)
- Sistema de pontuação baseado em chegada e precisão nos tiros
- Ranking final ordenado por pontuação total

## 🚀 Como executar

### Pré-requisitos
- JDK 8 ou superior
- Git instalado

### Passo a passo
1. Clone o repositório:
```bash
git clone https://github.com/GabrielGit10110/TriatloModerno.git
```

2. Acesse o diretório do projeto:
```bash
cd TriatloModerno
```

3. Compile o código:
```bash
javac -d bin src/*.java
```

4. Execute a aplicação:
```bash
java -cp bin Main
```

## 📊 Etapas da Prova

1. **🏃 Corrida (3km)**: 20-25 metros a cada 30ms
2. **🎯 Tiro ao alvo (3 disparos)**: 0-10 pontos por tiro, 0.5-3s por disparo
3. **🚴 Ciclismo (5km)**: 30-40 metros a cada 40ms

## 🛠️ Tecnologias utilizadas
- Java SE
- Programação multithread
- Sincronização de recursos limitados (armas)
- Ordenação de resultados
- Geração de números aleatórios
- JDK 8+

## 📋 Exemplo de saída

```
🏁 PROVA DE TRIATLO MODERNO INICIADA! 🏁
25 atletas | 5 armas disponíveis | 3 etapas

Atleta #6 começou a corrida!
Atleta #3 começou a corrida!
Atleta #21 começou a corrida!
Atleta #22 começou a corrida!
Atleta #11 começou a corrida!
Atleta #4 começou a corrida!
Atleta #10 começou a corrida!
...
Atleta #25 começou a corrida!
...

Atleta #13 começou a prova de tiro!
#13 acertou 5 no 3° alvo
Atleta #13 finalizou a prova de tiro com 11 pontos!
...

Atleta #13 começou a bicicleta!
Atleta #19 finalizou em 2° lugar com 247 pontos (240 base + 7 tiro)
Atleta #22 finalizou em 3° lugar com 243 pontos (230 base + 13 tiro)
...

Fazendo contagem final dos pontos...

 RANKING FINAL 
1° lugar: Atleta #10 com 267 pontos
2° lugar: Atleta #19 com 247 pontos
...
24° lugar: Atleta #18 com 31 pontos
25° lugar: Atleta #6 com 20 pontos

```

## ⚙️ Sistema de Pontuação

- **Pontos por chegada**: 250 para o 1º, 240 para o 2º, ..., 10 para o 25º
- **Pontos por tiro**: 0-10 por disparo (3 disparos por atleta)
- **Pontuação total**: Pontos de chegada + soma dos pontos dos tiros

## 👨‍💻 Desenvolvido por
[GabrielGit10110](https://github.com/GabrielGit10110)
