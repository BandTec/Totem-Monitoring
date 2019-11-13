package monitoramento;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;
import oshi.software.os.OperatingSystem.ProcessSort;
import oshi.util.FormatUtil;
import oshi.util.Util;


public class Totem {

    private final String sistemaOperacional;
    private Long cpu;
    private Double memoria;
    private Double disco;
    private LocalDateTime tempo;
    private String processos;

    private final SystemInfo si;
    private final HardwareAbstractionLayer hw;
    private final OperatingSystem os;

    public Totem() {
        si = new SystemInfo();

        hw = si.getHardware();
        os = si.getOperatingSystem();

        sistemaOperacional = hw.getComputerSystem().toString();
    }
    

    private String capturarProcessos(final OperatingSystem os, final GlobalMemory memory) {

        StringBuilder builder = new StringBuilder();

        final List<OSProcess> procs;
        procs = Arrays.asList(os.getProcesses(30, ProcessSort.CPU));

        for (int i = 0; i < procs.size(); i++) {
            final OSProcess p = procs.get(i);

            String pid = String.valueOf(p.getProcessID());
            String name = p.getName();
            String cpuPorcentagem = String.valueOf(100d * (p.getKernelTime() + p.getUserTime()) / p.getUpTime()).substring(0, 7);
            Double memPorcentagem = 100d * p.getResidentSetSize() / memory.getTotal();
            builder.append(String.format("\n\t%-5d \t\t%-25s \t%.1f \t\t%.1f ",
                    Integer.parseInt(pid),
                    name,
                    Double.parseDouble(cpuPorcentagem),
                    memPorcentagem));
        }
        return builder.toString();
    }

    public void capturarDados() {

        setTempo(LocalDateTime.now());
        System.out.println("Captura de Tempo feita com sucesso");

        this.cpu = this.capturaCpu(hw.getProcessor());
        System.out.println("Captura de CPU feita com sucesso");

        this.memoria = this.capturaMemoria(hw.getMemory());
        System.out.println("Captura de Memória feita com sucesso");

        this.disco = this.capturaDisco();
        System.out.println("Captura de Disco feita com sucesso");

        this.processos = capturarProcessos(os, hw.getMemory());
        System.out.println("Captura de proecessos feita com sucesso");
        
        System.out.println("TODOS DADOS CAPTURADOS COM SUCESSO");
    }

    private Double capturaMemoria(GlobalMemory mem) {
        return formataDado(FormatUtil.formatBytes(mem.getAvailable()));
    }

    private Long capturaCpu(CentralProcessor pro) {
        long[] ticks = pro.getSystemCpuLoadTicks();
        Util.sleep(1000);
        Long cpuLong = Math.round(pro.getSystemCpuLoadBetweenTicks(ticks) * 100);
//        System.out.println("CPU arredondada: " + cpuLong);
        return cpuLong;
    }

    private Double capturaDisco() {
        long disponivel = 0;
        FileSystem fileSystem = os.getFileSystem();
        OSFileStore[] fsArray = fileSystem.getFileStores();
        for (OSFileStore oSFileStore : fsArray) {
            disponivel += oSFileStore.getUsableSpace();
        }
        return formataDado(FormatUtil.formatBytes(disponivel));
//        return Double.valueOf(FormatUtil.formatBytes(disponivel));
    }

    private Double formataDado(String dado) {
        dado = dado.replaceAll(",", ".");
        Double dadoFormatado = null;
        if (dado.contains("GiB")) {
            System.out.println("GiB encontrado");
            dado = dado.replace("GiB", "");
            dadoFormatado = dadoFormatado.valueOf(dado);
            return dadoFormatado;
        } else if (dado.contains("MiB")) {
            dado = dado.replace(" MiB", "");
            dadoFormatado = dadoFormatado.valueOf(dado);
            return dadoFormatado;
        }
        System.out.println("Deu em nada: " + dado);
        return dadoFormatado;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public Long getCpu() {
        return cpu;
    }

    public Double getDisco() {
        return disco;
    }

    public Double getMemoria() {
        return memoria;
    }

    public LocalDateTime getTempo() {
        return tempo;
    }

    public OperatingSystem getOs() {
        return os;
    }

    public String getProcessos() {
        return processos;
    }

    public void setCpu(Long cpu) {
        this.cpu = cpu;
    }

    public void setDisco(Double disco) {
        this.disco = disco;
    }

    public void setMemoria(Double memoria) {
        this.memoria = memoria;
    }

    public void setTempo(LocalDateTime tempo) {
        this.tempo = tempo;
    }
}
