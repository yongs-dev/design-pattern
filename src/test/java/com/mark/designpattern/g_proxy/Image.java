package com.mark.designpattern.g_proxy;

import lombok.extern.slf4j.Slf4j;

public class Image {}


/**
 * 대상 객체와 프록시 객체를 묶는 인터페이스 (다형성)
 */
interface IImage {

    /**
     * 이미지를 렌더링 하기 위해 구현체가 구현해야 하는 추상 메서드
     */
    void showImage();
}

/**
 * 대상 객체 (RealSubject)
 */
@Slf4j
class HighResolutionImage implements IImage {
    String image;

    HighResolutionImage(String path) {
        loadImage(path);
    }

    private void loadImage(String path) {
        try {
            Thread.sleep(1000);
            image = path;
        } catch (Exception ignored) {}

        log.info("{} 이미지 로딩 완료", path);
    }

    @Override
    public void showImage() {
        log.info("{} 이미지 출력", image);
    }
}

/**
 * 프록시 객체 (Proxy)
 */
class ImageProxy implements IImage {

    private final String path;

    ImageProxy(String path) {
        this.path = path;
    }

    @Override
    public void showImage() {
        IImage proxyImage = new HighResolutionImage(path);
        proxyImage.showImage();
    }
}
