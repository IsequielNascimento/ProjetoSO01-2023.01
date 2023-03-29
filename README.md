# ProjetoSO01-2023.01

<h3>Projeto I - Problema “Brincadeira de Crianças” </h3>

<p> Imagine N crianças que estão, a princípio, quietas. M (M < N) crianças inicialmente
possuem uma bola e as outras, não. De repente, sentem vontade de brincar com uma bola.
Com esse desejo incontrolável, as que já estão com a bola simplesmente brincam. As que
não têm bola correm ao cesto de bolas, que está inicialmente vazio e que suporta até K
bolas. Se o cesto possuir bolas, uma criança pega a bola e vai brincar feliz. Se o cesto
estiver vazio, ela fica esperando até que outra criança coloque uma bola no cesto. Quando
uma criança termina de brincar, ela tem que colocar a bola no cesto, mas se o cesto já
estiver cheio, ela segura a bola até que outra criança retire uma bola que já está no cesto, e
então solta sua bola no cesto e volta a ficar quieta. Admita que as crianças continuem
brincando e descansando (quieta) eternamente. Utilizando semáforos, modele esse
problema resolvendo os conflitos entre os N threads "criança". </p>

<h3> Entradas: </h3>

<p>  • Capacidade do cesto de bolas (K).
</br>• Criação do thread criança:
o A aplicação deve possuir um botão para que o usuário possa criar uma criança a
qualquer momento. Durante a criação de cada thread criança, os seguintes
parâmetros devem ser definidos:
</br>• Id = identificador da criança (número ou nome).
</br>• Bola? = define se a criança será instanciada com bola ou sem bola.
</br>• Tb = tempo de brincadeira (tempo que a criança fica brincando com a bola –
a criança não deve dormir durante este tempo).
</br>• Tq = tempo quieta (tempo que criança fica quieta após colocar a bola no
cesto – a criança não deve dormir durante este tempo). </p>

<h3> Saídas: </h3>
<p> A interface deverá atender aos seguintes requisitos:</br>
• Mostrar os dados de cada criança: identificador, tempo de brincadeira e tempo que fica
quieta.</br>
• Mostrar, a cada instante, o status de cada thread criança (brincado com a bola,
aguardando que outra criança coloque uma bola no cesto, aguardando que o cesto tenha
espaço para que ela coloque sua bola ou quieta).</br>
• Mostrar um log com os principais eventos de cada criança. </p>
