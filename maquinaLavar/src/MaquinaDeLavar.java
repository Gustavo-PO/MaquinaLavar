public class MaquinaDeLavar {

    public enum Estado {
        DESLIGADA,
        LIGADA,
        LAVANDO,
        PAUSADA,
        LAVAGEM_CONCLUIDA,
        CENTRIFUGANDO,
        CENTRIFUGACAO_CONCLUIDA
    }

    private Estado estado;
    private boolean tampaAberta;

    public MaquinaDeLavar() {
        this.estado = Estado.DESLIGADA;
        this.tampaAberta = false;
    }

    /*Liga a máquina.*/
    public void ligar() {
        if (estado != Estado.DESLIGADA) {
            throw new IllegalStateException("A máquina já está ligada.");
        }
        estado = Estado.LIGADA;
        System.out.println("[OK] Máquina ligada.");
    }

    /*Desliga a máquina.*/
    public void desligar() {
        if (estado == Estado.DESLIGADA) {
            throw new IllegalStateException("A máquina já está desligada.");
        }
        if (estado == Estado.LAVANDO || estado == Estado.CENTRIFUGANDO) {
            throw new IllegalStateException(
                "Não é possível desligar a máquina durante a lavagem ou a centrifugação.");
        }
        estado = Estado.DESLIGADA;
        System.out.println("[OK] Máquina desligada.");
    }

    /*Abre a tampa.*/
    public void abrirTampa() {
        if (estado == Estado.LAVANDO || estado == Estado.CENTRIFUGANDO) {
            throw new IllegalStateException(
                "Não é possível abrir a tampa durante a lavagem ou a centrifugação.");
        }
        tampaAberta = true;
        System.out.println("[OK] Tampa aberta.");
    }

    /*Fecha a tampa.*/
    public void fecharTampa() {
        tampaAberta = false;
        System.out.println("[OK] Tampa fechada.");
    }

    /*Inicia a lavagem.*/
    public void iniciarLavagem() {
        if (estado == Estado.DESLIGADA) {
            throw new IllegalStateException("Uma máquina desligada não pode iniciar uma lavagem.");
        }
        if (tampaAberta) {
            throw new IllegalStateException("A lavagem só pode ser iniciada com a tampa fechada.");
        }
        if (estado != Estado.LIGADA) {
            throw new IllegalStateException("Não é possível iniciar a lavagem no estado atual: " + estado);
        }
        estado = Estado.LAVANDO;
        System.out.println("[OK] Lavagem iniciada.");
    }

    /*Pausa a lavagem em andamento.*/
    public void pausarLavagem() {
        if (estado == Estado.DESLIGADA) {
            throw new IllegalStateException("Uma máquina desligada não pode ser pausada.");
        }
        if (estado != Estado.LAVANDO) {
            throw new IllegalStateException("Só é possível pausar quando a máquina está lavando.");
        }
        estado = Estado.PAUSADA;
        System.out.println("[OK] Lavagem pausada.");
    }

    /*Retoma uma lavagem que estava pausada.*/
    public void retomarLavagem() {
        if (estado != Estado.PAUSADA) {
            throw new IllegalStateException("Só é possível retomar uma lavagem que esteja pausada.");
        }
        estado = Estado.LAVANDO;
        System.out.println("[OK] Lavagem retomada.");
    }

    /*Marca a lavagem em andamento como concluída, liberando a centrifugação.*/
    public void concluirLavagem() {
        if (estado != Estado.LAVANDO) {
            throw new IllegalStateException("Não há lavagem em andamento para ser concluída.");
        }
        estado = Estado.LAVAGEM_CONCLUIDA;
        System.out.println("[OK] Lavagem concluída.");
    }

    /*Inicia a centrifugação.*/
    public void iniciarCentrifugacao() {
        if (estado != Estado.LAVAGEM_CONCLUIDA) {
            throw new IllegalStateException(
                "A centrifugação só pode ser iniciada depois que a lavagem estiver concluída.");
        }
        estado = Estado.CENTRIFUGANDO;
        System.out.println("[OK] Centrifugação iniciada.");
    }

    /*Marca a centrifugação em andamento como concluída.*/
    public void concluirCentrifugacao() {
        if (estado != Estado.CENTRIFUGANDO) {
            throw new IllegalStateException("Não há centrifugação em andamento para ser concluída.");
        }
        estado = Estado.CENTRIFUGACAO_CONCLUIDA;
        System.out.println("[OK] Centrifugação concluída. A máquina já pode ser desligada.");
    }

    public Estado getEstado() {
        return estado;
    }

    public boolean isTampaAberta() {
        return tampaAberta;
    }

    public String toString() {
        return "MaquinaDeLavar{estado=" + estado + ", tampaAberta=" + tampaAberta + "}";
    }
}
