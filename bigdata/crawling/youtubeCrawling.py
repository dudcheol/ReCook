import time
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import pandas as pd

image_list = list() # 썸네일을 받을 수 있는 주소 저장용 리스트
title_list = list() # 제목을 저장하는 리스트
view_list = list() # 조회수 리스트

url_list = list() # 해당 url
channel_list = list()  # 채널명
channel_img_list = list() # 채널 이미지

def get_image_title(url):
    # 웹 드라이버 초기화
    driver_path = "C:/SSAFY/Webdriver/chromedriver.exe"
    driver = webdriver.Chrome(driver_path)
    driver.implicitly_wait(30) # or bigger second
    # 열고자 하는 채널 -> 동영상 목록으로 된 url 페이지를 엶
    driver.get(url)

    idx = 1
    while True:
        try:
            # 영상 썸네일 : img 안에 있는 src 이용
            img_xpath = '/html/body/ytd-app/div/ytd-page-manager/ytd-search/div[1]/ytd-two-column-search-results-renderer/div/ytd-section-list-renderer/div[2]/ytd-item-section-renderer/div[3]/ytd-video-renderer['+str(idx)+']/div[1]/ytd-thumbnail/a/yt-img-shadow/img'
            # img_xpath = '/html/body/ytd-app/div/ytd-page-manager/ytd-search/div[1]/ytd-two-column-search-results-renderer/div/ytd-section-list-renderer/div[2]/ytd-item-section-renderer/div[3]/ytd-shelf-renderer[1]/div[1]/div[2]/ytd-vertical-list-renderer/div[1]/ytd-video-renderer[1]/div[1]/ytd-thumbnail/a/yt-img-shadow/img'

            # 영상 제목 : a 태그 안에 있는 title 이용
            title_xpath = '/html/body/ytd-app/div/ytd-page-manager/ytd-search/div[1]/ytd-two-column-search-results-renderer/div/ytd-section-list-renderer/div[2]/ytd-item-section-renderer/div[3]/ytd-video-renderer['+str(idx)+']/div[1]/div/div[1]/div/h3/a/yt-formatted-string'
            # title_xpath = '/html/body/ytd-app/div/ytd-page-manager/ytd-search/div[1]/ytd-two-column-search-results-renderer/div/ytd-section-list-renderer/div[2]/ytd-item-section-renderer/div[3]/ytd-video-renderer['+str(idx)+']/div[1]/div/div[1]/div/h3/a'
            # title_xpath = '/html/body/ytd-app/div/ytd-page-manager/ytd-browse/ytd-two-column-browse-results-renderer/div[1]/ytd-section-list-renderer/div[2]/ytd-item-section-renderer/div[3]/ytd-grid-renderer/div[1]/ytd-grid-video-renderer['+str(idx)+']/div[1]/div[1]/div[1]/h3/a'

            # 영상 조회수 : span 안에 있는 것 이용
            viewcnt_xpath = '/html/body/ytd-app/div/ytd-page-manager/ytd-search/div[1]/ytd-two-column-search-results-renderer/div/ytd-section-list-renderer/div[2]/ytd-item-section-renderer/div[3]/ytd-video-renderer['+str(idx)+']/div[1]/div/div[1]/ytd-video-meta-block/div[1]/div[2]/span[1]'
            # viewcnt_xpath = '/html/body/ytd-app/div/ytd-page-manager/ytd-browse/ytd-two-column-browse-results-renderer/div[1]/ytd-section-list-renderer/div[2]/ytd-item-section-renderer/div[3]/ytd-grid-renderer/div[1]/ytd-grid-video-renderer['+str(idx)+']/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/span[1]'

            # 영상 url : a 태그 안에 있는 href 이용
            url_xpath = '/html/body/ytd-app/div/ytd-page-manager/ytd-search/div[1]/ytd-two-column-search-results-renderer/div/ytd-section-list-renderer/div[2]/ytd-item-section-renderer/div[3]/ytd-video-renderer['+str(idx)+']/div[1]/ytd-thumbnail/a'

            # 채널명 : a 안에 글씨 이용
            channel_xpath = '/html/body/ytd-app/div/ytd-page-manager/ytd-search/div[1]/ytd-two-column-search-results-renderer/div/ytd-section-list-renderer/div[2]/ytd-item-section-renderer/div[3]/ytd-video-renderer['+str(idx)+']/div[1]/div/div[2]/ytd-channel-name/div/div/yt-formatted-string/a'

            # 채널 이미지 : img 안에 있는 src 이용
            channel_img_xpath = '/html/body/ytd-app/div/ytd-page-manager/ytd-search/div[1]/ytd-two-column-search-results-renderer/div/ytd-section-list-renderer/div[2]/ytd-item-section-renderer/div[3]/ytd-video-renderer['+str(idx)+']/div[1]/div/div[2]/a/yt-img-shadow/img'

            # 이미지가 곧바로 로드 되지 않을 때, 20초간 강제로 기다림
            img = WebDriverWait(driver, 20).until(EC.presence_of_all_elements_located((By.XPATH, img_xpath)))
            if img is None:
                print(idx, 'img is not loaded.')

            # 동영상 목록을 추가 불러오기 위해 스크롤 내림
            if idx % 3 == 0 :
                driver.execute_script('window.scrollBy(0, 1080);')
                time.sleep(2)
                driver.execute_script('window.scrollBy(0, 1080);')
                time.sleep(2)
                driver.execute_script('window.scrollBy(0, 1080);')
                time.sleep(2)

            # 썸네일 주소를 리스트에 저장
            image = driver.find_element_by_xpath(img_xpath)
            img_url = image.get_attribute('src')
            image_list.append(img_url)

            # 타이틀을 리스트에 저장
            title = driver.find_element_by_xpath(title_xpath)
            title_list.append(title.text)

            # 조회수를 리스트에 저장
            view = driver.find_element_by_xpath(viewcnt_xpath)
            view_list.append(view.text)

            # 해당 url을 리스트에 저장
            urls = driver.find_element_by_xpath(url_xpath)
            real_url = urls.get_attribute('href')
            url_list.append(real_url)

            # 채널명을 리스트에 저장
            channel = driver.find_element_by_xpath(channel_xpath)
            channel_list.append(channel.text)

            # 채널 이미지를 리스트에 저장
            channel_img = driver.find_element_by_xpath(channel_img_xpath)
            channel_img_url = channel_img.get_attribute('src')
            channel_img_list.append(channel_img_url)

            print(idx, title.text, view.text, real_url, channel.text, channel_img_url)

            idx += 1

        except Exception as e:
            print()
            print(e)
            break
    assert len(image_list) == len(title_list)
    return image_list, title_list, view_list, url_list, channel_list, channel_img_list

# 검색하고자 하는 keyword 설정
keyword = '초간단 레시피'
url1 = 'https://www.youtube.com/results?search_query={}'.format(keyword)
image1, title1, view1, urls1, channel1, channelimg1 = get_image_title(url1)

youtubeDic = {
    '썸네일': image_list,
    '제목': title_list,
    '조회수': view_list,
    '주소': url_list,
    '채널명': channel_list,
    '채널사진': channel_img_list
}

youtubeDf = pd.DataFrame(youtubeDic)

youtubeDf.to_csv('recipevideo.csv', encoding='', index=False)
