# Generated by Django 3.0.4 on 2020-03-29 09:34

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('gamify', '0004_auto_20200329_0709'),
    ]

    operations = [
        migrations.RenameField(
            model_name='challengeinstance',
            old_name='challenge',
            new_name='masterChallenge',
        ),
    ]
