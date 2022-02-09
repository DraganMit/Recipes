# Recipes REST API Service

## Recipes

#### Get a single recipe by Id

`GET /api/recipe/{id}`

__Response body__:
```
{
   "id": "<string>",
   "category": "<string>",
   "date":"<string>",
   "description": "<string>",
   "ingredients": ["<string>",...],
   "directions":["<string>",...],
}
```

#### Search recipes by name

`GET /api/recipe/search`

#### Search recipes by category

`GET /api/recipe/search`

#### Create a recipe

`POST /api/recipe/new`

__Request body__:
```
{
   "name": "<string>",
   "category": "<string>",
   "description": "<string>",
   "ingredients": ["<string>",...],
   "directions":["<string>",...],
}
```
__Response body__:
```
{
   "id": "<string>",
}
```

#### Delete a recipe by Id

`DELETE /api/recipe/{id}`

#### Update recipe with given Id

`PUT /api/recipe/{id}`

## Users

#### Register a new user

`POST /api/register`

__Request body__:
```
{
   "email": "<string>",
   "password": "<string>"
}
```
