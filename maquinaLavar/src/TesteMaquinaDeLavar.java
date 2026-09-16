public class TesteMaquinaDeLavar {

    public static void main(String[] args) {
        MaquinaDeLavar maquina = new MaquinaDeLavar();

        System.out.println("===== TENTATIVAS INVÁLIDAS ANTES DE LIGAR =====");
        tentar(() -> maquina.iniciarLavagem());
        tentar(() -> maquina.pausarLavagem());
        tentar(() -> maquina.desligar());

        System.out.println("\n===== CICLO COMPLETO =====");
        maquina.ligar();
        tentar(() -> maquina.ligar());

        maquina.abrirTampa();
        maquina.fecharTampa();

        maquina.iniciarLavagem();
        System.out.println("Estado atual: " + maquina.getEstado());

        tentar(() -> maquina.abrirTampa());
        tentar(() -> maquina.desligar());
        tentar(() -> maquina.iniciarCentrifugacao());

        maquina.pausarLavagem();
        System.out.println("Estado atual: " + maquina.getEstado());

        maquina.retomarLavagem();
        System.out.println("Estado atual: " + maquina.getEstado());

        maquina.concluirLavagem();
        System.out.println("Estado atual: " + maquina.getEstado());

        maquina.iniciarCentrifugacao();
        System.out.println("Estado atual: " + maquina.getEstado());

        tentar(() -> maquina.abrirTampa());
        tentar(() -> maquina.desligar());

        maquina.concluirCentrifugacao();
        System.out.println("Estado atual: " + maquina.getEstado());

        maquina.abrirTampa();
        maquina.desligar();
        System.out.println("Estado final: " + maquina.getEstado());
    }

    private static void tentar(Runnable operacao) {
        try {
            operacao.run();
        } catch (IllegalStateException e) {
            System.out.println("[BLOQUEADO] " + e.getMessage());
        }
    }
}
