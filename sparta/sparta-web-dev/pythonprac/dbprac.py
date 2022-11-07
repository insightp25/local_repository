from pymongo import MongoClient
client = MongoClient('mongodb+srv://asyncpy:1122@nestcluster.9hndn.mongodb.net/test')
db = client.dbsparta

# insert / find / update / delete

doc = {'name' : 'smith', 'age': 22}
# db.users.insert_one(doc)

# same_ages = list(db.users.find({'age':21},{'_id':False}))
# same_ages = list(db.users.find({},{'_id':False}))
# for person in same_ages:
#     print(person)
    
# user = db.users.find_one({'name':'meh'},{'_id':False})
# print(user['name'])

# db.users.update_one({'name':'meh'},{'$set': {'name': 'wasabi'}})

db.users.delete_one({'name':'wasabi', 'age': 21})