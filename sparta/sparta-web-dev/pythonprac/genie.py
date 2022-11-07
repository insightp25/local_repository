import requests
from bs4 import BeautifulSoup

headers = {'User-Agent' : 'Mozilla/5.0 (Windows NT 10.0; Win64; x64)AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36'}
data = requests.get('https://www.genie.co.kr/chart/top200?ditc=D&ymd=20220925&hh=02&rtm=Y&pg=1',headers=headers)

soup = BeautifulSoup(data.text, 'html.parser')

trs = soup.select('#body-content > div.newest-list > div > table > tbody > tr')
# print(trs)
for tr in trs:
    title = tr.select_one('td.info > a').text.strip()
    num = tr.text.strip().split()[0]
    artist = tr.select_one('td.info > a.artist.ellipsis').text
    print(num, title, artist)


# trs = soup.select('#body-content > div.newest-list > div > table > tbody > tr')
# for tr in trs:
#     a_tag = tr.select_one('td.info > a')
#     print(a_tag)

# a_tag = soup.select_one('#body-content > div.newest-list > div > table > tbody > tr:nth-child(1) > td.info > a.title.ellipsis')
# tmp = a_tag.text
# title = tmp.strip()
# print(title)


#body-content > div.newest-list > div > table > tbody > tr:nth-child(1) > td.number
#body-content > div.newest-list > div > table > tbody > tr:nth-child(4) > td.number

# td_tag = soup.select_one('#body-content > div.newest-list > div > table > tbody > tr:nth-child(1) > td.number')
# temp = td_tag.text.strip().split()[0]
# print(temp)


#body-content > div.newest-list > div > table > tbody > tr:nth-child(1) > td.info > a.artist.ellipsis
#body-content > div.newest-list > div > table > tbody > tr:nth-child(3) > td.info > a.artist.ellipsis
# a = soup.select_one('#body-content > div.newest-list > div > table > tbody > tr:nth-child(1) > td.info > a.artist.ellipsis')
# print(a.text)
