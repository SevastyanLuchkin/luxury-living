package ru.luxury.living.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.luxury.living.model.News;
import ru.luxury.living.repository.NewsRepository;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;

    public News create(News news) {
        return newsRepository.save(news);
    }

    public News update(Long id, News request) {
        News news = newsRepository.findById(id).orElseThrow();
        news.setTitleRu(request.getTitleRu());
        news.setDescriptionRus(request.getDescriptionRus());
        news.setImageId(request.getImageId());
        news.setNewsDate(request.getNewsDate());
        return newsRepository.save(news);
    }

    public void delete(Long id) {
        newsRepository.deleteById(id);
    }

    public News getById(Long id) {
        return newsRepository.findById(id)
                .orElseThrow();
    }

    public Page<News> getAll(Pageable pageable) {
        return newsRepository.findAll(pageable);
    }
}
