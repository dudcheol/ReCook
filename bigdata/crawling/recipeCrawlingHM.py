import requests
from bs4 import BeautifulSoup

baseUrl = 'https://haemukja.com/recipes/'

def PageCrawler(recipeUrl):
    url = baseUrl + recipeUrl

    page = requests.get(url)
    soup = BeautifulSoup(page.content, 'html.parser')

    recipe_title =''   # 레시피 제목
    recipe_source = []  # 레시피 재료(db저장용)
    recipe_source_string = ''  # 레시피 재료(프론트에서 사용)
    recipe_step_explain = '' #레시피 순서 설명
    recipe_step_img = '' #레시피 순서 이미지
    recipe_tag=[]
    recipe_time=''

    try:
        
        res = soup.find('section', 'sec_info')
        #레시피 제목
        title= res.find('div','aside')
        title= title.find('div','top')
        time = title
        title= title.find('h1')
        recipe_title=title.find('strong').get_text()

        #레시피 조리시간
        time=time.find('dl','info_basic')
        recipe_time += time.find('dd').get_text()+' '
        # print(recipe_time)

        #레시피 재료
        ingd = res.find('div', 'btm')
        recipe_time +=ingd.find('div','dropdown').get_text().replace(" ","") #몇인분
        ingd = ingd.find('ul')
        ingd = ingd.find_all('li')
        ingredient=''
        cnt=''

        for n in ingd:
            ingredient=n.find('span').get_text()# 재료
            cnt=n.find('em').get_text()# 갯수
            if(not cnt):
                cnt="적당히"
            recipe_source.append(ingredient)
            recipe_source_string+=ingredient+' '+cnt+' '
        # print(recipe_source_string)

        
        #조리 순서
        step= soup.find('section','sec_detail')
        step= step.find("section",'sec_rcp_step')
        step= step.find('ol')
        step= step.find_all('li')
        for n in step:
            recipe_step_img+=n.find('div','img-cover').find("img").get("src")+'\n'# 사진
            recipe_step_explain+=n.find('p').get_text()+'\n'# 설명
        # print(recipe_step_explain)
        # print(recipe_step_img)

        #태그 
        tag = soup.find('section','sec_detail')
        tag = tag.find('div','box_tag')
        tag = tag.find_all('a')
        for n in tag:
            recipe_tag.append(n.get_text())# 태그
        print(recipe_tag)
        print(recipe_time)

    except(AttributeError):
        return

    
    return 

    
if __name__ == "__main__":
    PageCrawler('734')# 제육볶음