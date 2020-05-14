const express = require('express')
var async = require('async');
const { MongoClient, ObjectID } = require('mongodb')
const app = express()
const port = process.env.PORT || 3000
const mongoose = require('mongoose')
app.use(express.json())
const data = require('./dataReviews')


console.log(data.length)


// DataBase Config
// const db = require('./config/keys').mongoURI;
const connectionURL = 'mongodb+srv://jugalbhatt123:Panujugu123@cluster0-cmlwg.mongodb.net/test?retryWrites=true&w=majority'
const databaseName = "TEST"


// Connect to MongoDB
MongoClient.connect(connectionURL, { useNewUrlParser: true, useUnifiedTopology: true }, (error, client) => {
    if (error) {
        return console.log('Unable to connect to database!')
    }

    const db = client.db(databaseName)
  
      console.log('MongoDB Connected')

    app.get('/time',async(req,res)=>{
            code = req.body.code
            var mysort = {REVIEW:-1}
            db.collection('user').find({ CODE: code }).sort(mysort).toArray((error, result) => {
            res.send(result)
           
        })
    })

    app.get('/time/app/:code',async(req,res)=>{
        if(!req.params.code){
            throw new Error('Enter subject code')
        }
            var mysort = {REVIEW:-1}
            db.collection('user').find({ CODE: req.params.code }).sort(mysort).toArray((error, result) => {
            res.send(result)
        })
    })

    app.get("/time/subjectCode",(req,res)=>{
        var mysort = {CODE: 1 };
        db.collection('user').find({}).project({TITLE: 1,_id: 0,CODE: 1,CREDITS:1}).sort(mysort).toArray((error,result)=>{
            const standardsList=result
            // res.send(result)
            function uniqurArray(standardsList){
        var a = standardsList.concat();
       for(var i=0; i<a.length; i++) {
           for(var j=i+1; j<a.length; j++) {
               if(a[i].CODE === a[j].CODE){
                   a.splice(j--, 1);
               }
           }
       }
    
       return a;
    }
    
    res.send(uniqurArray(standardsList))
    
        })
    
    })

    app.patch('/time',(req,res)=>{
        db.collection('user').updateMany({},{$set:{"REVIEW":0}})
            res.send()
               
    })
    app.get('/time/getLabs',async(req,res)=>{
        var mysort = {REVIEW:-1}
        db.collection('user').find({ TYPE:"ELA",FACULTY:req.body.name,CODE:req.body.code}).sort(mysort).toArray((error, result) => {
        res.send(result)
       
    })
})
    app.get('/time/teacher',async (req,res)=>{
        name = req.body.name
        code = req.body.code
        if(req.body.sort==='morn'){
            flag =1
        }
        else if (req.body.sort==='eve'){
            flag =0
        }
        else{
            throw new Error('Enter a valid sort Parameter')
        }
        db.collection('user').find({FACULTY:name,CODE:code,Flag:flag}).toArray((error,result)=>{
            res.send(result)
            console.log(result)
            
        })
        if(flag===1){
            app.get('/time',async(req,res)=>{
                db.collection('user').find({FACULTY:name,CODE:code,SLOT:{$regex:/^L3[0-9]|^L4[0-9]|^L5[[0-9]/}}).toArray((error,result)=>{
                    console.log(result)
                    res.send(result)
                })
            
            })
        }
        else{
            app.get('/time',async(req,res)=>{
                db.collection('user').find({FACULTY:name,CODE:code,SLOT:{$regex:/^L[0-9]|^L1[0-9]|^L2[0-9]/}}).toArray((error,result)=>{
                    console.log(result)
                    res.send(result)
                })
            
            })

        }
        

    })

    

    

    app.patch('/time/updateReviews',async (req,res)=>{
        async.eachSeries(data, function updateObject (obj, done) {
            // Model.update(condition, doc, callback)
            db.collection('user').updateMany({ FACULTY: obj.NAME }, { $set : { REVIEW: obj.RATINGS }}, done);
        }, function allDone (err) {
            // this will be called when all the updates are done or an error occurred during the iteration
        });
    })
    
    })
  


    app.listen(port, () => {
        console.log('Server is up on port ' + port)
    })
