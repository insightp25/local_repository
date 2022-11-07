import requests
from bs4 import BeautifulSoup

from pymongo import MongoClient
client = MongoClient('mongodb+srv://asyncpy:1122@nestcluster.9hndn.mongodb.net/test')
db = client.dbsparta

headers = {'User-Agent' : 'Mozilla/5.0 (Windows NT 10.0; Win64; x64)AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36'}
data = requests.get('https://movie.naver.com/movie/sdb/rank/rmovie.naver?sel=cur&date=20220913',headers=headers)

soup = BeautifulSoup(data.text, 'html.parser')

title = soup.select_one('#old_content > table > tbody > tr:nth-child(2) > td.title > div > a')

# print(title)
# print(title.text)
# print(title['href'])

#old_content > table > tbody > tr:nth-child(2)
#old_content > table > tbody > tr:nth-child(3)

#old_content > table > tbody > tr



# # title:
# #old_content > table > tbody > tr:nth-child(2) > td.title > div > a
# trs = soup.select('#old_content > table > tbody > tr')

# for tr in trs:
#     # a_tag = tr.select_one('td.title > div > a')
#     print(a_tag)
#     # if a_tag is not None:
#     #     title = a_tag.text
#     #     print(rank, title, )



# # score:
# # old_content > table > tbody > tr:nth-child(3) > td.point
# # old_content > table > tbody > tr:nth-child(2) > td.point

# scores = soup.select('#old_content > table > tbody > tr')
# for score in scores:
#     td_tag = score.select_one('td.point')
#     if td_tag is not None:
#         number = td_tag.text
#         print(number)
    
    
    
# # score
#old_content > table > tbody > tr:nth-child(3) > td.point
#old_content > table > tbody > tr:nth-child(4) > td.point


    
# all together
trs = soup.select('#old_content > table > tbody > tr')

for tr in trs:
    a_tag = tr.select_one('td.title > div > a')
    if a_tag is not None:
        rank = tr.select_one('td:nth-child(1) > img')['alt']
        title = a_tag.text
        score = tr.select_one('td.point').text
        doc = {
            'title': title,
            'star': score,
            'rank': rank
        }
        db.movies.insert_one(doc)
        

'''
- selector:
old_content > table > tbody > tr:nth-child(2) > td.title > div > a

- outerHTML
<a href="/movie/bi/mi/basic.naver?code=201641" title="공조2: 인터내셔날">공조2: 인터내셔날</a>

- JS path
document.querySelector("#old_content > table > tbody > tr:nth-child(2) > td.title > div > a")

- styles
    text-align: left;
    border-collapse: collapse;
    border-spacing: 0;
    white-space: nowrap;
    margin: 0;
    padding: 0;
    background-color: transparent;
    font-style: normal;
    font-size: 12px;
    font-family: "돋움", Dotum, "굴림", Gulim,Helvetica,AppleSDGothicNeo-Medium,AppleGothic, Sans-serif;
    text-decoration: none;
    color: #656565;
    
- XPath
//*[@id="old_content"]/table/tbody/tr[2]/td[2]/div/a

- full XPath
/html/body/div/div[4]/div/div/div/div/div[1]/table/tbody/tr[2]/td[2]/div/a
'''