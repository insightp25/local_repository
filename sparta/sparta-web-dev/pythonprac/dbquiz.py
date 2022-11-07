from pymongo import MongoClient
client = MongoClient("mongodb+srv://asyncpy:1122@nestcluster.9hndn.mongodb.net/test")
db = client.dbsparta

movie = db.movies.find_one({'title': '화양연화'},{'_id':False})
# print(movie['star'])
da_star = movie['star']

ho = list(db.movies.find({'star': da_star},{'_id':False}))
for mov in ho:
    print(mov['title'])
    
db.movies.update_one({'title': '화양연화'},{'$set': {'star': 100}})