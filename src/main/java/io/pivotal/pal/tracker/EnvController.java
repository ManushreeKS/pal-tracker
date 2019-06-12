package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
public class EnvController {
    private String port;
    private String memorylimit;
    private String instanceIndex;
    private String instanceAddress;

    public EnvController(@Value("${port:NOT SET}") String port, @Value("${memory.limit:NOT SET}") String memoryLimit,
                         @Value("${cf.instance.index:NOT SET}") String instanceIndex,@Value("${cf.instance.addr:NOT SET}") String instanceAddress){
        this.port = port;
        this.memorylimit = memoryLimit;
        this.instanceIndex = instanceIndex;
        this.instanceAddress = instanceAddress;
    }

    @GetMapping("/env")
    public Map<String, String> getEnv () {
        Map<String, String> envmap = new HashMap<String, String>();
        envmap.put("PORT", this.port);
        envmap.put("MEMORY_LIMIT", this.memorylimit);
        envmap.put("CF_INSTANCE_INDEX", this.instanceIndex);
        envmap.put("CF_INSTANCE_ADDR", this.instanceAddress);
        return envmap;
    }
}
