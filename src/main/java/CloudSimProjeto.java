import org.cloudsimplus.core.CloudSimPlus;
import org.cloudsimplus.vms.Vm;
import org.cloudsimplus.vms.VmSimple;

import org.cloudsimplus.cloudlets.Cloudlet;
import org.cloudsimplus.cloudlets.CloudletSimple;
import org.cloudsimplus.utilizationmodels.UtilizationModelFull;

import org.cloudsimplus.datacenters.Datacenter;
import org.cloudsimplus.datacenters.DatacenterSimple;
import org.cloudsimplus.hosts.Host;
import org.cloudsimplus.hosts.HostSimple;
import org.cloudsimplus.resources.Pe;
import org.cloudsimplus.resources.PeSimple;
import org.cloudsimplus.cloudlets.Cloudlet;
import org.cloudsimplus.core.CloudSimPlus;

import org.cloudsimplus.brokers.DatacenterBroker;
import org.cloudsimplus.brokers.DatacenterBrokerSimple;


import java.util.ArrayList;
import java.util.List;

public class CloudSimProjeto {
    public static void main(String[] args ) {
        CloudSimPlus simulation = new CloudSimPlus();

        List<Host> hostList = new ArrayList<>();

        List<Pe> peList = new ArrayList<>();
        peList.add(new PeSimple(10000));
        peList.add(new PeSimple(10000));
        peList.add(new PeSimple(10000));

        Host host = new HostSimple(4096, 10000, 50000, peList);
        hostList.add(host);

        Datacenter datacenter = new DatacenterSimple(simulation,hostList); 
        DatacenterBroker broker = new DatacenterBrokerSimple(simulation);

        System.out.println("Broker criado com sucesso!");
        System.out.println("Datacenter criado com sucesso!");

        List<Vm> vmList = new ArrayList<>();
        
        Vm vm1 = new VmSimple(1000, 1);
        vm1.setRam(1024);
        vm1.setSize(10240);

        Vm vm2 = new VmSimple(1000, 1);
        vm2.setRam(1024);
        vm2.setSize(10240);

        Vm vm3 = new VmSimple(1000, 1);
        vm3.setRam(1024);
        vm3.setSize(10240);

        vmList.add(vm1);
        vmList.add(vm2);
        vmList.add(vm3);
        broker.submitVmList(vmList);

List<Cloudlet> cloudletList = new ArrayList<>();

for (int i = 0; i < 5; i++) {
    Cloudlet cloudlet = new CloudletSimple(1000, 1);
    cloudlet.setUtilizationModel(new UtilizationModelFull());
    cloudletList.add(cloudlet);
}

System.out.println("5 tarefas criadas com sucesso!");
broker.submitCloudletList(cloudletList);
simulation.start();
System.out.println("\n===== RESULTADOS DAS TAREFAS =====");
for (Cloudlet cloudlet : broker.getCloudletFinishedList())  {
    System.out.println(
        "Tarefa" + cloudlet.getId()
        + " | VM " + cloudlet.getVm().getId()
        + " | Tempo de execução: "
        + (cloudlet.getFinishTime() - cloudlet.getStartTime())
        + " segundos "
    );
}
System.out.println("3 VMs criadas com sucesso!");
System.out.println("VM 1 - Servidor Web");
System.out.println("VM 2 - Banco de Dados");
System.out.println("VM 3 - Servidor de Aplicação");
    }
}