# Projeto Campo Minado em JavaSwing

![image](https://github.com/joaohenriDev/Campo-minado/assets/118625621/4c0d1018-60c8-4a89-84ce-3693f8d56783)

O projeto Campo Minado é uma implementação do clássico jogo de Campo Minado em Java utilizando a biblioteca gráfica JavaSwing. O objetivo do jogo é revelar todas as células do tabuleiro que não contêm minas, evitando detonar as células que têm minas ocultas. Cada célula revelada mostra o número de minas adjacentes, ajudando o jogador a identificar as células seguras. O projeto foi desenvolvido utilizando o padrão de projeto Observer para lidar com eventos e atualizações de estado no jogo.

# Funcionalidades

- Tabuleiros gerados aleatoriamente: O jogo gera automaticamente um tabuleiro com o tamanho e a quantidade de minas desejada pelo usuário.

- Interface Gráfica: A interface gráfica é construída com o JavaSwing, permitindo uma experiência de jogo interativa e amigável.

- Marcação de Células: O jogador pode marcar células suspeitas de conterem minas, para evitar cliques acidentais.
  

# Instruções de uso

Para Iniciar você deve clonar este repositorio

![image](https://github.com/joaohenriDev/Campo-minado/assets/118625621/e704acd5-73fd-46a6-9720-20c3741d6784)

1 - Abra o projeto em sua IDE Java preferida, como Eclipse ou IntelliJ.

2 - Compile e execute o programa.

3 - a interface gráfica do jogo, selecione o tamanho do tabuleiro e a quantidade de minas desejada.

4 - Clique em uma célula para revelá-la. O número na célula indica quantas minas estão presentes nas células adjacentes. Caso a célula revele uma mina, o jogo termina.

5 - Para marcar uma célula suspeita de conter uma mina, clique com o botão direito do mouse.

6 - Continue jogando até limpar o tabuleiro ou acertar uma mina.

7 - O jogo vai reiniciar automaticamente após você perder ou ganhar o jogo.


# Padrão de Projeto Observer

O padrão de projeto Observer foi implementado neste projeto para gerenciar eventos e atualizações de estado do jogo. Através do padrão Observer, as células do tabuleiro notificam o painel do jogo sobre suas mudanças de estado, como revelação de células ou marcação de minas. O painel do jogo, por sua vez, atualiza a interface gráfica e os componentes do jogo de acordo com essas notificações. Isso permite uma comunicação eficiente entre as partes do jogo, tornando-o mais modular e de fácil manutenção.


# Requisitos de Sistema

. Java Development Kit (JDK) 8 ou superior.

# Agradecimentos

Agradecemos por utilizar o Campo Minado em JavaSwing. Esperamos que você se divirta jogando este clássico jogo! Em caso de dúvidas, problemas ou sugestões, não hesite em abrir uma issue no repositório. Sua contribuição é fundamental para melhorar o projeto. Obrigado pela sua participação! Em breve versão mobile!

# Autor

João Henrique de Oliveira

https://www.linkedin.com/in/jo%C3%A3o-henrique-8625341b9/
