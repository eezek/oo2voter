package br.edu.ulbra.election.voter.client;

import br.edu.ulbra.election.voter.output.v1.GenericOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class ElectionClientService {

    private final ElectionClient electionClient;

    @Autowired
    public ElectionClientService(ElectionClient electionClient){
        this.electionClient = electionClient;
    }

    public String hasVotes(Long voterId){
        return this.electionClient.hasVotes(voterId);
    }

    @FeignClient(value="election-service", url="${url.election-service}")
    private interface ElectionClient {

        @GetMapping("/v1/result/voter/{voterId}")
        String hasVotes(@PathVariable(name = "voterId") Long voterId);
;
    }
}
