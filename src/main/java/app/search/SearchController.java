package app.search;

import app.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SearchController {

    private final WebClient webClient;
    private final Utils utils;

    public SearchController(WebClient.Builder builder, Utils utils) {
        this.webClient = builder
            .exchangeStrategies(ExchangeStrategies
                .builder()
                .codecs(configurer -> configurer
                    .defaultCodecs()
                    .maxInMemorySize(16 * 1024 * 1024)
                ).build()
            )
            .baseUrl("http://openlibrary.org/search.json").build();
        this.utils = utils;
    }

    @GetMapping(value = "/search")
    public String getSearchResults(@RequestParam String query, Model model) {
        SearchResult searchResult = this.webClient.get()
            .uri("?q={query}", query)
            .retrieve()
            .bodyToMono(SearchResult.class)
            .block();
        List<SearchResultBook> resultBooks = searchResult.getDocs().stream().
            limit(10).
            map(b-> {
                b.setKey(b.getKey().replace("/works/",""));
                b.setCover_i(utils.getCoverLargeImageUrl(b.getCover_i(), false));
                return b;
            }).
            collect(Collectors.toList());
        model.addAttribute("searchResults", resultBooks);
        return "search";
    }
}
