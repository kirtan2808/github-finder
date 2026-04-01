package com.githubfinder.githubfinder.service;

import com.githubfinder.githubfinder.model.GithubUser;
import org.json.JSONObject;
import org.json.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class GithubService {

    // 🔥 USER DATA
    public GithubUser getUser(String username) {

        try {
            String url = "https://api.github.com/users/" + username;

            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(url, String.class);

            JSONObject obj = new JSONObject(response);

            GithubUser user = new GithubUser();
            user.setName(obj.optString("name"));
            user.setLogin(obj.optString("login"));
            user.setBio(obj.optString("bio"));
            user.setAvatar_url(obj.optString("avatar_url"));
            user.setPublic_repos(obj.optInt("public_repos"));

            return user;

        } catch (Exception e) {
            return null;
        }
    }

    // 🔥 REPO LIST (NEW)
    public List<String> getRepos(String username) {

        try {
            String url = "https://api.github.com/users/" + username + "/repos";

            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(url, String.class);

            JSONArray array = new JSONArray(response);

            List<String> repoNames = new ArrayList<>();

            for (int i = 0; i < Math.min(array.length(), 5); i++) {
                JSONObject obj = array.getJSONObject(i);
                repoNames.add(obj.getString("name"));
            }

            return repoNames;

        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}