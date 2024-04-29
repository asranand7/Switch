package in.practise.uuid;

import com.fasterxml.uuid.Generators;

import java.util.UUID;

public class UuidMain {
    public static void main(String[] args) throws InterruptedException {

        for(int i = 0;i<100;i++){
            UUID uuid = Generators.timeBasedEpochGenerator().generate();
            Thread.sleep(1);
            System.out.println(uuid);
        }

    }
}
