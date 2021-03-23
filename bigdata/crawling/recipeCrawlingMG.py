import requests
from bs4 import BeautifulSoup

baseUrl = 'https://haemukja.com/recipes/'

# def CrawlingBetweenRanges(mydb, startRecipeId, endRecipeId):
#     for i in range(startRecipeId, endRecipeId):
#         if i % 10 == 0:
#             print("count: " + str(i))
#         res = PageCrawler(str(i))
#         if res is None:
#             continue

#         menuId = mydb.insert_menu(res[0][0], baseUrl+str(i))
#         for key, value in res[1].items():
#             for name in value:
#                 if key == "[재료]" or key == "[양념]":
#                     mydb.insert_ingredient(menuId, name)

def PageCrawler(recipeUrl):
    url = baseUrl + recipeUrl

    page = requests.get(url)
    soup = BeautifulSoup(page.content, 'html.parser')

    recipe_title = []  # 레시피 제목
    recipe_source = {}  # 레시피 재료
    
    # recipe_step = [] #레시피 순서

    try:
        #레시피 재료
        res = soup.find('div', 'view2_summary')
        res = res.find('h3')
        recipe_title.append(res.get_text())
        res = soup.find('div', 'view2_summary_info')
        recipe_title.append(res.get_text().replace('\n', ''))
        res = soup.find('div', 'ready_ingre3')


    except(AttributeError):
        return

    # 재료 찾는 for문 가끔 형식에 맞지 않는 레시피들이 있어 try/ except 해준다
    try:
        for n in res.find_all('ul'):
            source = []
            title = n.find('b').get_text()
            recipe_source[title] = ''
            for tmp in n.find_all('li'):
                tempSource = tmp.get_text().replace('\n', '').replace(' ', ' ')
                source.append(tempSource.split("    ")[0])

            recipe_source[title] = source
    except (AttributeError):
        return

    recipe_all = [recipe_title, recipe_source]  #제목, 재료

    print(recipe_title)
    for key, value in recipe_all[1].items():
        for name in value:
            if key == "[재료]" or key == "[양념]":
                print(name)
    
#레시피 조리 순서
    soup = BeautifulSoup(page.content, 'html.parser')
    try:
        res_cook =soup.find('div', 'view_step')
        # res_cook=soup1.find('div','view_step_cont')
        for n in res_cook.find_all('div','media-body'):
            step=n.get_text()#조리 설명
            print(step)
           
        res_cook=soup.findAll('div', 'view_step_cont')       
        for n in res_cook:
            n=n.find("img") #이미지
            print(n.get('src'))
  

    except(AttributeError):
        return
    
    return (recipe_all)

    
if __name__ == "__main__":
    PageCrawler('734')# 제육볶음