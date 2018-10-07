/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.response;

import api.model.Challenge;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 *
 * @author fernanda
 */
public class ChallengesResponse {
    @SerializedName("challenges")
    private List<Challenge> challenges;

    public ChallengesResponse(List<Challenge> challenges) {
        this.challenges = challenges;
    }
    
}
