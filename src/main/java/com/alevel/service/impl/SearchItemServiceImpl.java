package com.alevel.service.impl;

//public class SearchItemServiceImpl implements SearchItemService {
//    @Override
//    public List<String> fetchSuggestions(String query) {
//        QueryBuilder queryBuilder = QueryBuilders
//                .wildcardQuery("name", query + "*");
//        Query searchQuery = new NativeSearchQueryBuilder()
//                .withFilter(queryBuilder)
//                .withPageable(PageRequest.of(0, 5))
//                .build();
//        SearchHits<ItemIndex> searchSuggestions =
//                elasticsearchOperations.search(searchQuery,
//                        BookIndex.class,
//                        IndexCoordinates.of(BOOK_INDEX));
//        final List<String> suggestions = new ArrayList<>();
//        searchSuggestions.getSearchHits().forEach(searchHit-> suggestions.add(searchHit.getContent().getName()));
//        return suggestions;
//    }
//}
