using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ASD;
public class HashTableLineProbing
{
    private readonly int _size;
    private readonly PersonModel?[] _data;
    private readonly int _step;
    public HashTableLineProbing(int size, int step)
    {
        _size = size;
        _data = new PersonModel?[_size];
        _step = step;
    }

    public void Print()
    {
        int i = 1;
        foreach (var element in _data)
        {
            if (element == null || element.IsDeleted)
            {
                Console.WriteLine($"Элемент {i} - null");
            }
            else
            {
                Console.WriteLine($"Элемент {i} - {element.Name} {element.Age}");
            }
            i++;
        }
    }

    public void Insert(PersonModel personModel)
    {
        int index = GetIndex(personModel);

        if (_data[index] == null || _data[index].IsDeleted)
        {
            _data[index] = personModel;
            return;
        }

        for (int i = 1; index + i * _step < _size; i++)
        {
            if (_data[index + i * _step] == null || _data[index + i * _step].IsDeleted)
            {
                _data[index + i * _step] = personModel;
                return;
            }
        }

        Console.WriteLine("Таблица переполнена");
    }

    public void Delete(PersonModel personModel)
    {
        int index = GetIndex(personModel);

        for (int i = 0; index + i * _step < _size; i++)
        {
            if (_data[index + i * _step].Age == personModel.Age && _data[index + i * _step].Name == personModel.Name && _data[index + i * _step].IsDeleted == personModel.IsDeleted)
            {
                _data[index + i * _step].IsDeleted = true;
            }
        }
    }

    public bool Find(PersonModel personModel)
    {
        int index = GetIndex(personModel);

        if (_data[index] == null)
        {
            return false;
        }
        else
        {
            for (int i = 0; index + i * _step < _size; i++)
            {
                if (_data[index + i * _step] == null)
                {
                    return false;
                }
                if (_data[index + i * _step].Age == personModel.Age && _data[index + i * _step].Name == personModel.Name && _data[index + i * _step].IsDeleted == personModel.IsDeleted)
                {
                    return true;
                }
            }

            return false;
        }
    }

    private int GetIndex(PersonModel personModel)
        => (personModel.Age * personModel.Age + personModel.Name.Length * personModel.Name.Length + personModel.Age * personModel.Name.Length) % _size;

}
