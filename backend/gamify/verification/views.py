import os
from datetime import datetime, time

from django.http import JsonResponse
from django.core.files.storage import FileSystemStorage
from rest_framework.decorators import api_view

from gamify.models import User

from azure.cognitiveservices.vision.face import FaceClient
from msrest.authentication import CognitiveServicesCredentials
from google.cloud import vision

AZURE_KEY = os.environ['AZURE_KEY']
AZURE_ENDPOINT = 'https://lahacks.cognitiveservices.azure.com/'
face_client = FaceClient(AZURE_ENDPOINT, CognitiveServicesCredentials(AZURE_KEY))


@api_view(['POST'])
def verifyApple(request):
    """
    inputs JSON with two fields "user_id", "image"
    output: JSON with two fields "verified" either true or false, "code"
    """
    user = User.objects.get(id=request.POST['user_id'])
    img = request.FILES["image"]
    image = vision.types.Image(content=img.read())
    client = vision.ImageAnnotatorClient()
    response = client.label_detection(image=image)
    labels = response.label_annotations
    
    if "Fruit" not in [label.description for label in labels]:
        return JsonResponse({"verified": False}, status=201)

    # Facial Recognition

    code = get_image_code(client, image)
    return JsonResponse({"verified": True, "code": code}, status=201)


@api_view(['POST'])
def verifyVideo(request):
    """
    inputs JSON with two fields "user_id", "image"
    output: JSON with two fields "verified" either true or false, "code"
    """
    user = User.objects.get(id=request.POST['user_id'])
    img = request.FILES["image"]
    image = vision.types.Image(content=img.read())
    client = vision.ImageAnnotatorClient()
    response = client.face_detection(image=image)
    faces = response.face_annotations

    if len(faces) < 2:
        return JsonResponse({"verified": False}, status=201)

    # facial recognition

    code = get_image_code(client, image)
    return JsonResponse({"verified": True, "code": code}, status=201)


@api_view(['POST'])
def verifyBook(request):
    """
    inputs JSON with two fields "user_id", "image"
    output: JSON with two fields "verified" either true or false, "code"
    """
    user = User.objects.get(id=request.POST['user_id'])
    img = request.FILES["image"]
    image = vision.types.Image(content=img.read())
    client = vision.ImageAnnotatorClient()
    response = client.label_detection(image=image)
    labels = response.label_annotations

    if "Illustration" not in [label.description for label in labels]:
        return JsonResponse({"verified": False}, status=201)

    # facial recognition

    code = get_image_code(client, image)
    return JsonResponse({"verified": True, "code": code}, status=201)


@api_view(['POST'])
def verifySunrise(request):
    """
    inputs JSON with two fields "user_id", "image"
    output: JSON with two fields "verified" either true or false, "code"
    """
    user = User.objects.get(id=request.POST['user_id'])
    img = request.FILES["image"]
    # User needs to be send request between 4:30-5:30 AM
    now_time = datetime.now().time()
    if  now_time < time(4,30) or now_time > time(5,30):
        return JsonResponse({"verified": False}, status=201)

    # facial recognition

    img.seek(0)
    code = get_image_code(vision.ImageAnnotatorClient(), vision.types.Image(content=img.read()))
    return JsonResponse({"verified": True, "code": code}, status=201)


def get_image_code(client, image):
    """
    Return unique code that user must have within their image
    input: img from request
    output: string
    """
    response = client.text_detection(image=image)

    text = response.full_text_annotation.text.strip()
    return text[-4:]
